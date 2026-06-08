# Java1234微信小程序个人健康管理系统

本科毕业设计级别的个人健康管理系统，包含后台管理端、微信小程序端和 Spring Boot 后端。

## 项目结构

```
mini_program/
├── db/                  # 数据库建表脚本和测试数据
├── server/              # Spring Boot 4 后端
├── client/              # Vue3 + Element Plus 后台管理
├── weixin/              # 微信小程序
└── uploads/             # 文件上传目录
```

## 环境要求

- JDK 17+
- MySQL 8.0（端口 3308）
- Node.js 18+
- 微信开发者工具
- 阿里云百炼 API Key（AI 功能）

## Docker 部署（Alpine 3）

> MySQL 使用外部已有实例，不打包 MySQL 镜像。

### 方式一：Docker Compose（推荐，前端 + 后端）

```bash
# 1. 配置环境变量
copy .env.example .env
# 编辑 .env，填入 MySQL 连接信息和 OPENAI_API_KEY

# 2. 一键构建并启动
docker compose up -d --build

# 3. 查看日志
docker compose logs -f
```

访问地址：
- 后台管理：http://localhost （默认 80 端口）
- 后端 API：http://localhost:8080

### 方式二：分别打包镜像

```bash
# 后端
cd server
docker build -t health-management-server:alpine3 .

# 前端
cd client
docker build -t health-management-client:alpine3 .

# 先启动后端
docker run -d --name health-server -p 8080:8080 \
  -e SPRING_DATASOURCE_URL="jdbc:mysql://192.168.16.146:3306/health_management?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true" \
  -e SPRING_DATASOURCE_USERNAME=root \
  -e SPRING_DATASOURCE_PASSWORD=你的密码 \
  -e OPENAI_API_KEY=你的百炼密钥 \
  -v health-uploads:/app/uploads \
  health-management-server:alpine3

# 再启动前端（需与后端在同一 Docker 网络，或使用 compose）
docker run -d --name health-client -p 80:80 \
  --link health-server:server \
  health-management-client:alpine3
```

镜像均基于 **Alpine 3.20**（后端 OpenJDK 17 JRE，前端 Nginx；前端构建阶段使用 `node:22-alpine3.20` 以满足 Vite 8 要求）。

**前端构建失败排查**：若出现 `rolldown-binding.linux-x64-musl.node` 找不到，请执行 `docker compose build --no-cache client` 重新构建，勿将宿主机 `node_modules` 复制进镜像。

## 快速启动（本地开发）

### 1. 数据库

```bash
mysql -h localhost -P 3308 -u root -p < db/health_management.sql
```

默认数据库账号密码在 `server/src/main/resources/application.yml` 中配置（默认 root/root）。

### 2. 后端

```bash
cd server
# 配置百炼 API Key（AI 功能必需）
copy .env.example .env
# 编辑 .env，填入 OPENAI_API_KEY=你的百炼API密钥
# Windows 启动
mvnw.cmd spring-boot:run
```

后端地址：http://localhost:8080

### 3. 后台管理

```bash
cd client
npm install
npm run dev
```

访问：http://localhost:5173
默认账号：`admin` / `123456`

### 4. 微信小程序

1. 用微信开发者工具导入 `weixin` 目录
2. 详情 → 本地设置 → 勾选「不校验合法域名」
3. 测试账号：`zhangsan` / `123456`

## 功能模块

| 模块 | 后台管理 | 微信小程序 |
|------|---------|-----------|
| 用户管理 | ✅ | 登录/注册 |
| 健康档案 | ✅ | ✅ |
| 饮食记录 | ✅ | ✅ |
| 运动记录 | ✅ | ✅ |
| 睡眠记录 | ✅ | ✅ |
| 健康提醒 | ✅ | ✅ |
| 健康资讯 | ✅ 发布 | ✅ 浏览 |
| AI智能客服 | - | ✅ |
| 数据统计 | ✅ 图表看板 | 今日概览 |

## 技术栈

- **后端**: Spring Boot 4.0.6 + MyBatis + Spring AI 2.0 + Spring Security
- **后台**: Vue 3.5 + Element Plus + ECharts + Vite
- **小程序**: 微信原生 + TypeScript
- **数据库**: MySQL 8.0
- **AI**: 阿里云百炼 qwen3.7-plus（Spring AI OpenAI 兼容模式）

## 默认测试账号

| 角色 | 用户名 | 密码 |
|------|--------|------|
| 管理员 | admin | 123456 |
| 用户 | zhangsan | 123456 |
| 用户 | lisi | 123456 |
