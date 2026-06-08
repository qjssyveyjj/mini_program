<template>
  <div class="page-container">
    <el-row :gutter="20" class="stat-row">
      <el-col :span="6" v-for="item in cardList" :key="item.key">
        <div class="stat-card" :style="{ borderTop: `3px solid ${item.color}` }">
          <div class="stat-icon" :style="{ background: item.color + '20', color: item.color }">
            <el-icon :size="28"><component :is="item.icon" /></el-icon>
          </div>
          <div class="stat-info">
            <div class="stat-value">{{ cards[item.key] || 0 }}</div>
            <div class="stat-label">{{ item.label }}</div>
          </div>
        </div>
      </el-col>
    </el-row>
    <el-row :gutter="20" style="margin-top: 20px">
      <el-col :span="14">
        <div class="chart-card">
          <h3>近7天健康记录趋势</h3>
          <div ref="trendChart" style="height: 320px"></div>
        </div>
      </el-col>
      <el-col :span="10">
        <div class="chart-card">
          <h3>用户性别分布</h3>
          <div ref="genderChart" style="height: 320px"></div>
        </div>
      </el-col>
    </el-row>
    <el-row :gutter="20" style="margin-top: 20px">
      <el-col :span="12">
        <div class="chart-card">
          <h3>各类记录占比</h3>
          <div ref="recordChart" style="height: 300px"></div>
        </div>
      </el-col>
      <el-col :span="12">
        <div class="chart-card welcome-card">
          <h3>系统概览</h3>
          <div class="welcome-content">
            <p>欢迎使用 <strong>Java1234微信小程序个人健康管理系统</strong></p>
            <p>本系统提供健康档案、饮食、运动、睡眠记录管理，以及AI智能健康顾问服务。</p>
            <el-divider />
            <el-descriptions :column="2" border size="small">
              <el-descriptions-item label="注册用户">{{ cards.userCount || 0 }} 人</el-descriptions-item>
              <el-descriptions-item label="健康档案">{{ cards.healthRecordCount || 0 }} 条</el-descriptions-item>
              <el-descriptions-item label="饮食记录">{{ cards.dietRecordCount || 0 }} 条</el-descriptions-item>
              <el-descriptions-item label="运动记录">{{ cards.exerciseRecordCount || 0 }} 条</el-descriptions-item>
              <el-descriptions-item label="睡眠记录">{{ cards.sleepRecordCount || 0 }} 条</el-descriptions-item>
              <el-descriptions-item label="健康资讯">{{ cards.articleCount || 0 }} 篇</el-descriptions-item>
            </el-descriptions>
          </div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script setup>
import { ref, onMounted, nextTick } from 'vue'
import * as echarts from 'echarts'
import request from '../utils/request'

const cards = ref({})
const trendChart = ref(null)
const genderChart = ref(null)
const recordChart = ref(null)

const cardList = [
  { key: 'userCount', label: '注册用户', icon: 'User', color: '#409eff' },
  { key: 'healthRecordCount', label: '健康档案', icon: 'Document', color: '#67c23a' },
  { key: 'dietRecordCount', label: '饮食记录', icon: 'Apple', color: '#e6a23c' },
  { key: 'exerciseRecordCount', label: '运动记录', icon: 'Trophy', color: '#f56c6c' }
]

onMounted(async () => {
  const res = await request.get('/stats/overview')
  const data = res.data
  cards.value = data.cards || {}
  await nextTick()
  initTrendChart(data.healthTrend || [])
  initGenderChart(data.genderDistribution || [])
  initRecordChart(data.recordTypes || [])
})

function initTrendChart(trendData) {
  const chart = echarts.init(trendChart.value)
  chart.setOption({
    tooltip: { trigger: 'axis' },
    grid: { left: 40, right: 20, top: 30, bottom: 30 },
    xAxis: { type: 'category', data: trendData.map(d => d.name), axisLine: { lineStyle: { color: '#ddd' } } },
    yAxis: { type: 'value', splitLine: { lineStyle: { type: 'dashed' } } },
    series: [{
      type: 'line', smooth: true, data: trendData.map(d => d.value),
      areaStyle: { color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
        { offset: 0, color: 'rgba(102,126,234,0.4)' }, { offset: 1, color: 'rgba(102,126,234,0.05)' }
      ])},
      lineStyle: { color: '#667eea', width: 3 }, itemStyle: { color: '#667eea' }
    }]
  })
}

function initGenderChart(genderData) {
  const chart = echarts.init(genderChart.value)
  chart.setOption({
    tooltip: { trigger: 'item' },
    color: ['#409eff', '#f56c6c', '#909399'],
    series: [{
      type: 'pie', radius: ['40%', '70%'], center: ['50%', '55%'],
      data: genderData.map(d => ({ name: d.name, value: Number(d.value) })),
      label: { formatter: '{b}: {c}人 ({d}%)' },
      emphasis: { itemStyle: { shadowBlur: 10, shadowColor: 'rgba(0,0,0,0.2)' } }
    }]
  })
}

function initRecordChart(recordData) {
  const chart = echarts.init(recordChart.value)
  chart.setOption({
    tooltip: { trigger: 'axis' },
    grid: { left: 80, right: 20, top: 20, bottom: 30 },
    xAxis: { type: 'value' },
    yAxis: { type: 'category', data: recordData.map(d => d.name) },
    series: [{
      type: 'bar', data: recordData.map(d => Number(d.value)),
      itemStyle: {
        borderRadius: [0, 6, 6, 0],
        color: new echarts.graphic.LinearGradient(0, 0, 1, 0, [
          { offset: 0, color: '#667eea' }, { offset: 1, color: '#764ba2' }
        ])
      },
      barWidth: 20
    }]
  })
}
</script>

<style scoped>
.stat-row .stat-card {
  display: flex;
  align-items: center;
  gap: 16px;
  background: #fff;
  border-radius: 12px;
  padding: 20px;
  box-shadow: 0 4px 20px rgba(0,0,0,0.06);
}
.stat-icon {
  width: 56px; height: 56px; border-radius: 12px;
  display: flex; align-items: center; justify-content: center;
}
.stat-value { font-size: 28px; font-weight: 700; color: #333; }
.stat-label { font-size: 13px; color: #999; margin-top: 4px; }
.chart-card {
  background: #fff; border-radius: 12px; padding: 20px;
  box-shadow: 0 4px 20px rgba(0,0,0,0.06);
}
.chart-card h3 { font-size: 16px; color: #333; margin-bottom: 12px; }
.welcome-content p { color: #666; line-height: 1.8; margin-bottom: 8px; }
</style>
