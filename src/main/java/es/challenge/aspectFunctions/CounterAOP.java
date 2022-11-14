package es.challenge.aspectFunctions;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.boot.logging.LogFile;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class CounterAOP {
	
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(LogFile.class);

    @Around("@annotation(es.challenge.aspectFunctions.Counter)")
    public Object contarEjecucion(ProceedingJoinPoint joint) throws Throwable {

        long startTime = System.currentTimeMillis();
        Object started = joint.proceed();
        long timeEjecution = System.currentTimeMillis() - startTime;
		CounterAOP.log.trace("Time ejecution " + joint.getSignature() + " in " + timeEjecution +"ms");
        return started;
    }
}