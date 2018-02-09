# hello world api service
locals {
  # name of the service in ECS
  service_name                     = "hello-world-DemoGraphQL"

  # ALB health check url
  health_check_path                = "/health"

  # interval of number of seconds to perform health check
  health_check_interval            = 60

  # number of times that an unhealthy instance must be healthy in order to be added back into the ALB
  health_check_healthy_threshold   = 2

  # number of a times that a healthy instance fails before removale from the ALB
  health_check_unhealthy_threshold = 5

  # how long to wait for the health check path. A timeout indicates a failure.
  health_check_timeout             = 30

  # grace period for starting health check if needed.
  health_check_grace_period_seconds   = 120

  # the ALB port (usually 80)
  alb_port                         = 80

  # the IAM role to use
  task_iam_role                    = "${data.terraform_remote_state.config.default_api_task_role_arn}"

  scaling_memory_threshold         = "200"

  victorops_notify_group           = "@sam.flint@corvesta.com"

  # Cluster name to deploy the service to. Ask DevOps if you don't know.
  # Options are: api / web / pm_pro_enterprise / pm_pro_enterprise_web/\
  cluster_name                     = "api"

  # The port your container is listening for traffic on
  container_port                   = 8080

  cpu_scaling_metric_period        = 180
}
