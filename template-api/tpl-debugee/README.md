# Java debugee 启用调试

```shell
# suspend=n 不会在启动时等待调试器连接
-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=localhost:8011
# suspend=y 会在启动时等待调试器连接
-agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=localhost:8011
```