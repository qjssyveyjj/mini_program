# ============================================================
# Java1234微信小程序个人健康管理系统 - 前端 Docker 镜像
# 基础镜像: Alpine 3.20
# ============================================================

# ---------- 阶段1: Node 构建 ----------
FROM alpine:3.20 AS builder

RUN apk add --no-cache nodejs npm

WORKDIR /app

COPY package.json package-lock.json ./
RUN npm ci

COPY index.html vite.config.js ./
COPY public public
COPY src src

RUN npm run build

# ---------- 阶段2: Nginx 运行 ----------
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
