package gradle_mybatis_spring_study.config;

import java.util.Collections;
import java.util.HashMap;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;

@EnableAspectJAutoProxy
@Configuration
public class ContextTransaction {

	@Autowired
	PlatformTransactionManager transactionManager;

	public TransactionInterceptor transactionAdvice() {
		
		NameMatchTransactionAttributeSource txAttributeSource = new NameMatchTransactionAttributeSource();

		RuleBasedTransactionAttribute txAttribute = new RuleBasedTransactionAttribute();
		txAttribute.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
		txAttribute.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

		HashMap<String, TransactionAttribute> txAttributes = new HashMap<String, TransactionAttribute>();
		txAttributes.put("tr*", txAttribute);
		txAttributeSource.setNameMap(txAttributes);

		TransactionInterceptor txAdvice = new TransactionInterceptor();
		txAdvice.setTransactionManager(transactionManager);
		txAdvice.setTransactionAttributeSource(txAttributeSource);

		return txAdvice;
	}

	@Bean
	public Advisor transactionAdvisor() {
		
		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		pointcut.setExpression("execution(public * gradle_mybatis_spring_study.service..*Service.*(..))");
		return new DefaultPointcutAdvisor(pointcut, transactionAdvice());
	}

}
