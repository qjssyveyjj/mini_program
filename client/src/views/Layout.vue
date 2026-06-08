<template>
  <el-container class="layout-container">
    <el-aside width="220px" class="sidebar">
      <div class="logo">
        <el-icon :size="24"><Histogram /></el-icon>
        <span>健康管理系统</span>
      </div>
      <el-menu :default-active="route.path" router background-color="#1d2b3a" text-color="#bfcbd9" active-text-color="#409eff">
        <el-menu-item index="/home"><el-icon><DataAnalysis /></el-icon><span>数据看板</span></el-menu-item>
        <el-menu-item index="/user"><el-icon><User /></el-icon><span>用户管理</span></el-menu-item>
        <el-menu-item index="/health-record"><el-icon><Document /></el-icon><span>健康档案</span></el-menu-item>
        <el-menu-item index="/diet"><el-icon><Apple /></el-icon><span>饮食记录</span></el-menu-item>
        <el-menu-item index="/exercise"><el-icon><Trophy /></el-icon><span>运动记录</span></el-menu-item>
        <el-menu-item index="/sleep"><el-icon><MoonNight /></el-icon><span>睡眠记录</span></el-menu-item>
        <el-menu-item index="/reminder"><el-icon><Bell /></el-icon><span>健康提醒</span></el-menu-item>
        <el-menu-item index="/article"><el-icon><Reading /></el-icon><span>健康资讯</span></el-menu-item>
      </el-menu>
    </el-aside>
    <el-container>
      <el-header class="header">
        <span class="page-title">{{ route.meta.title }}</span>
        <div class="header-right">
          <span class="admin-name">{{ adminStore.admin?.nickname || '管理员' }}</span>
          <el-button type="danger" text @click="handleLogout">退出</el-button>
        </div>
      </el-header>
      <el-main class="main-content">
        <router-view />
      </el-main>
    </el-container>
  </el-container>
</template>

<script setup>
import { useRoute, useRouter } from 'vue-router'
import { useAdminStore } from '../store'

const route = useRoute()
const router = useRouter()
const adminStore = useAdminStore()

function handleLogout() {
  adminStore.logout()
  router.push('/login')
}
</script>

<style scoped>
.layout-container { height: 100vh; }
.sidebar { background: #1d2b3a; overflow: hidden; }
.logo {
  height: 60px;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 8px;
  color: #fff;
  font-size: 16px;
  font-weight: bold;
  border-bottom: 1px solid rgba(255,255,255,0.1);
}
.header {
  background: #fff;
  display: flex;
  align-items: center;
  justify-content: space-between;
  box-shadow: 0 1px 4px rgba(0,0,0,0.08);
  padding: 0 24px;
}
.page-title { font-size: 18px; font-weight: 600; color: #333; }
.header-right { display: flex; align-items: center; gap: 12px; }
.admin-name { color: #666; font-size: 14px; }
.main-content { background: #f0f2f5; padding: 0; }
</style>
