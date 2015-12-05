package asia.ait.sad.eip.example.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.junit.Test;

public class ActivitiTest {

	ProcessEngine processEngine;
	RepositoryService repositoryService;
	RuntimeService runtimeService;
	Deployment deployment;
	TaskService taskService;

	@Test
	public void deploy() {

		// Create Activiti process engine.
		// This looks for activiti.cfg.xml in the classpath.
		processEngine = ProcessEngines.getDefaultProcessEngine();

		// Get Activiti services
		repositoryService = processEngine.getRepositoryService();
		runtimeService = processEngine.getRuntimeService();

		// Deploy the process definition
		deployment = repositoryService.createDeployment()
				.addClasspathResource("financial-report.bpmn20.xml").deploy();

		taskService = processEngine.getTaskService();

	}

}
