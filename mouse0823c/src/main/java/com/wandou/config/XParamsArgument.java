package com.wandou.config;

import com.wandou.annotation.XParam;
import com.wandou.common.BusinessException;
import com.wandou.enumeration.ReturnCodeEnum;
import com.wandou.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * xhc
 * 方法的扩展参数信息标志,这个参数可指定用户编号，角色，触发场景等
 */

@Slf4j
@Component
public class XParamsArgument implements HandlerMethodArgumentResolver {

    @Autowired
    private RedisUtil redisUtil;


    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        log.info("进入 supportsParameter()");
        return parameter.hasParameterAnnotation(XParam.class);
    }

    /**
     * 不好使呢
     * 2020-01-17 好使了，需要 com.wandou.config.MyWebMVCConfig#addArgumentResolvers(java.util.List)
     *
     * @param parameter
     * @param mavContainer
     * @param webRequest
     * @param binderFactory
     * @return
     * @throws Exception
     */
    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {
        XParam annotation = parameter.getParameterAnnotation(XParam.class);
        log.info("annotation: {}", annotation);
        switch (annotation.value()) {
            case UID:
                String token = webRequest.getHeader("token");
                log.info("token: {}", token);
                if (StringUtils.isBlank(token) && annotation.validate()) {
                    throw new BusinessException(ReturnCodeEnum.BAD_TOKEN);
                }
                long uid = redisUtil.getUserIdByToken(token);
                if (uid <= 0) {
                    throw new BusinessException(ReturnCodeEnum.BAD_TOKEN);
                }
                return uid;
            case Scope:
                String scope = webRequest.getHeader("scope");
                if (StringUtils.isBlank(scope) && annotation.validate()) {
                    throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "非法的角色");
                }
                if (StringUtils.isNotBlank(annotation.defaultValue())) {
                    return annotation.defaultValue();
                }
                return scope;
            case OpenID:
                String openid = webRequest.getHeader("uauth");
                if (StringUtils.isBlank(openid) && annotation.validate()) {
                    throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "错误的登录信息");
                }
                if (StringUtils.isNotBlank(annotation.defaultValue())) {
                    return annotation.defaultValue();
                }
                return openid;
            case IP:
                String IP = webRequest.getHeader("ip");
                if (StringUtils.isBlank(IP) && annotation.validate()) {
                    throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "错误的请求来源");
                }
                if (StringUtils.isNotBlank(annotation.defaultValue())) {
                    return annotation.defaultValue();
                }
                return IP;
            default:
                throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "暂不支持的xParam类型");
        }
    }
}
