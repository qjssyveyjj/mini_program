# 后端服务 - Java1234个人健康管理系统

## 技术栈

- Spring Boot 4.0.6
- Spring AI 2.0.0-M8
- MyBatis 4.0.1
- MySQL 8.0
- Java 17

## 配置说明

编辑 `src/main/resources/application.yml`：

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3308/health_management
    username: root
    password: root
  ai:
    openai:
      api-key: ${OPENAI_API_KEY}
      base-url: https://dashscope.aliyuncs.com/compatible-mode/v1
```

## Docker 打包（Alpine 3.20）

```bash
# 构建镜像
docker build -t health-management-server:alpine3 .

# 运行（使用环境变量覆盖配置）
docker run -d -p 8080:8080 \
  -e OPENAI_API_KEY=sk-xxxxxxxx \
  -e SPRING_DATASOURCE_URL=jdbc:mysql://host:3306/health_management?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true \
  -e SPRING_DATASOURCE_USERNAME=root \
  -e SPRING_DATASOURCE_PASSWORD=yourpassword \
  -v uploads:/app/uploads \
  health-management-server:alpine3
```

也可在项目根目录使用 `docker compose up -d --build` 与前端一起启动（MySQL 使用外部实例）。

## 本地启动

```bash
# 1. 复制环境变量配置文件并填入百炼 API Key
copy .env.example .env
# 编辑 .env，将 OPENAI_API_KEY 改为你的真实密钥

# 2. 启动（Windows，需在 server 目录下执行）
mvnw.cmd spring-boot:run
```

`.env` 文件示例：

```properties
OPENAI_API_KEY=sk-xxxxxxxx
```

> `.env` 已加入 `.gitignore`，不会被提交到 Git。

## API 接口

| 接口 | 说明 |
|------|------|
| POST /api/admin/login | 管理员登录 |
| POST /api/user/login | 用户登录 |
| POST /api/user/register | 用户注册 |
| GET /api/stats/overview | 统计数据 |
| POST /api/ai/chat | AI对话 |
| POST /api/file/upload | 文件上传 |
| GET/POST/PUT/DELETE /api/* | 各模块CRUD |
