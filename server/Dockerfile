# ============================================================
# Java1234微信小程序个人健康管理系统 - 后端 Docker 镜像
# 基础镜像: Alpine 3.20
# ============================================================

# ---------- 阶段1: Maven 构建 ----------
FROM alpine:3.20 AS builder

RUN apk add --no-cache openjdk17-jdk maven bash

WORKDIR /build

COPY pom.xml .
COPY mvnw mvnw.cmd ./
COPY .mvn .mvn
COPY src src

RUN chmod +x mvnw && ./mvnw package -DskipTests -B -q

# ---------- 阶段2: 运行镜像 ----------
FROM alpine:3.20

LABEL maintainer="Java1234"
LABEL description="Java1234微信小程序个人健康管理系统后端"

RUN apk add --no-cache openjdk17-jre-headless wget tzdata \
    && cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
    && echo "Asia/Shanghai" > /etc/timezone \
    && apk del tzdata

WORKDIR /app

RUN addgroup -S app && adduser -S app -G app \
    && mkdir -p /app/uploads \
    && chown -R app:app /app

COPY --from=builder /build/target/*.jar app.jar

USER app

EXPOSE 8080

ENV JAVA_OPTS="-Xms256m -Xmx512m" \
    FILE_UPLOAD_DIR=/app/uploads \
    TZ=Asia/Shanghai

HEALTHCHECK --interval=30s --timeout=5s --retries=3 \
    CMD wget -qO- http://localhost:8080/api/stats/overview || exit 1

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
