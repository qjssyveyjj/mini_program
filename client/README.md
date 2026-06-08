# 后台管理 - Java1234个人健康管理系统

## 技术栈

- Vue 3.5 + Vite 8
- Element Plus
- ECharts
- Axios + Pinia + Vue Router

## 本地开发启动

```bash
npm install
npm run dev
```

访问 http://localhost:5173，默认账号 admin / 123456

> 开发模式下 `/api` 请求由 Vite 代理到 `http://localhost:8080`

## Docker 打包（Alpine 3.20）

```bash
docker build -t health-management-client:alpine3 .
```

或通过项目根目录 `docker compose up -d --build` 与后端一起启动。

访问 http://localhost（Nginx 反向代理 `/api` 到后端）

## 页面

- 数据看板（ECharts 图表）
- 用户管理
- 健康档案 / 饮食 / 运动 / 睡眠记录
- 健康提醒
- 健康资讯
