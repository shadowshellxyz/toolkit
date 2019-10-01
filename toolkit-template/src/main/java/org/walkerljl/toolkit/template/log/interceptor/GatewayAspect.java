//package org.walkerljl.toolkit.template.log.interceptor;
//
//import java.util.Set;
//
//import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion.User;
//import org.walkerljl.toolkit.standard.exception.AppException;
//import org.walkerljl.toolkit.standard.model.param.BaseParam;
//
///**
// *
// * @author xingxun
// */
//public class GatewayAspect {
//
//    private        DingDingServiceClient dingDingServiceClient;
//    private        UserService           userService;
//
//    @Around("execution(* com.alipay.cxbiz.kainsight.gateway.function..*Func.*(..))")
//    public Object handleControllerMethod(ProceedingJoinPoint pjp) {
//        try {
//            Object[] args = pjp.getArgs();
//
//            Set<String> administratorIds = KaInsightConstants.getAdministratorIds();
//
//            for (Object arg : args) {
//                fillUserSession(arg, administratorIds);
//            }
//
//            return pjp.proceed();
//
//        } catch (Throwable e) {
//            throw new AppException(e);
//        } finally {
//            SessionContext.remove();
//        }
//    }
//
//
//}