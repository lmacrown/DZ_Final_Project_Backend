package com.douzone.aspect;

import org.apache.ibatis.binding.MapperMethod.ParamMap;
import org.apache.ibatis.session.SqlSession;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class QueryLoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Autowired
    private SqlSession sqlSession;

    @Around("execution(* *..dao.*.*(..))")
    public Object logSqlAndParams(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        Object[] methodArgs = joinPoint.getArgs();
        String sqlQuery = "";
        String statementId = sqlSession.getConfiguration().getMappedStatementNames()
                .stream()
                .filter(name -> name.endsWith("." + methodName))
                .findFirst()
                .orElse(null);
        
        if (methodArgs.length == 0) {
            logger.warn("No method arguments found for method: {}", methodName);
            return joinPoint.proceed();
        }
        if (statementId == null) {
            logger.warn("MappedStatement not found for method: {}", methodName);
            return joinPoint.proceed();
        }
       
        sqlQuery = sqlSession.getConfiguration().getMappedStatement(statementId).getBoundSql(methodArgs[0]).getSql();
        Object params = methodArgs[0];
       
        logger.info("\nExecuting SQL query: {}", "\n\t"+sqlQuery);
        if (params instanceof ParamMap) {
            logger.info("Parameters: {}", ((ParamMap<?>) params).entrySet().stream()
                    .map(entry -> entry.getKey() + "=" + entry.getValue())
                    .reduce((param1, param2) -> param1 + ", " + param2)
                    .orElse("None"));
        } else {
            logger.info("Parameters: {}", params);
        }

        Object result = joinPoint.proceed();
        
        logger.info("Result: {}", result);

        return result;
    }
}