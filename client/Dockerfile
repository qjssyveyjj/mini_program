# ============================================================
# Java1234微信小程序个人健康管理系统 - 前端 Docker 镜像
# 运行镜像: Alpine 3.20
# ============================================================

# ---------- 阶段1: Node 构建 ----------
# 使用官方 Node 22 Alpine 镜像（满足 Vite 8 对 Node 20.18+/22.12+ 的要求，
# 并正确安装 rolldown 的 linux-x64-musl 原生绑定）
FROM node:22-alpine3.20 AS builder

WORKDIR /app

COPY package.json package-lock.json ./
# 在容器内重新安装依赖，确保 rolldown 原生模块与 musl 环境匹配
RUN npm ci

COPY index.html vite.config.js ./
COPY public public
COPY src src

RUN npm run build

# ---------- 阶段2: Nginx 运行（Alpine 3.20）----------
FROM alpine:3.20

LABEL maintainer="Java1234"
LABEL description="Java1234微信小程序个人健康管理系统前端"

RUN apk add --no-cache nginx \
    && mkdir -p /run/nginx /usr/share/nginx/html

COPY nginx.conf /etc/nginx/http.d/default.conf
COPY --from=builder /app/dist /usr/share/nginx/html

EXPOSE 80

HEALTHCHECK --interval=30s --timeout=5s --retries=3 \
    CMD wget -qO- http://localhost/ || exit 1

CMD ["nginx", "-g", "daemon off;"]
