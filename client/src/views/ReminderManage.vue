<template>
  <div class="page-container">
    <div class="table-card">
      <div class="search-bar">
        <el-input v-model="keyword" placeholder="搜索标题/用户" clearable style="width: 240px" />
        <el-button type="primary" @click="loadData">搜索</el-button>
        <el-button type="success" @click="openDialog()">新增提醒</el-button>
      </div>
      <el-table :data="tableData" stripe border>
        <el-table-column prop="id" label="ID" width="60" />
        <el-table-column prop="nickname" label="用户" width="100" />
        <el-table-column prop="reminderType" label="类型" width="80" />
        <el-table-column prop="title" label="标题" />
        <el-table-column prop="remindTime" label="提醒时间" width="100" />
        <el-table-column prop="isEnabled" label="状态" width="80">
          <template #default="{ row }">
            <el-tag :type="row.isEnabled ? 'success' : 'info'">{{ row.isEnabled ? '启用' : '禁用' }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="160" fixed="right">
          <template #default="{ row }">
            <el-button type="primary" link @click="openDialog(row)">编辑</el-button>
            <el-button type="danger" link @click="handleDelete(row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination class="pagination" v-model:current-page="page" :page-size="size" :total="total" layout="total, prev, pager, next" @current-change="loadData" />
    </div>
    <el-dialog v-model="dialogVisible" :title="form.id ? '编辑' : '新增'" width="480px">
      <el-form :model="form" label-width="80px">
        <el-form-item label="用户ID"><el-input-number v-model="form.userId" :min="1" /></el-form-item>
        <el-form-item label="类型">
          <el-select v-model="form.reminderType">
            <el-option value="喝水" /><el-option value="吃药" /><el-option value="体检" /><el-option value="运动" />
          </el-select>
        </el-form-item>
        <el-form-item label="标题"><el-input v-model="form.title" /></el-form-item>
        <el-form-item label="内容"><el-input v-model="form.content" type="textarea" /></el-form-item>
        <el-form-item label="时间"><el-time-picker v-model="form.remindTime" value-format="HH:mm:ss" /></el-form-item>
        <el-form-item label="启用"><el-switch v-model="form.isEnabled" :active-value="1" :inactive-value="0" /></el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="handleSave">保存</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import request from '../utils/request'

const keyword = ref(''), page = ref(1), size = ref(10), total = ref(0)
const tableData = ref([]), dialogVisible = ref(false), form = ref({})

onMounted(() => loadData())
async function loadData() {
  const res = await request.get('/reminder/page', { params: { keyword: keyword.value, page: page.value, size: size.value } })
  tableData.value = res.data.records; total.value = res.data.total
}
function openDialog(row) {
  form.value = row ? { ...row } : { userId: 1, reminderType: '喝水', isEnabled: 1 }
  dialogVisible.value = true
}
async function handleSave() {
  if (form.value.id) await request.put('/reminder', form.value); else await request.post('/reminder', form.value)
  ElMessage.success('保存成功'); dialogVisible.value = false; loadData()
}
async function handleDelete(id) {
  await ElMessageBox.confirm('确定删除？'); await request.delete(`/reminder/${id}`)
  ElMessage.success('删除成功'); loadData()
}
</script>
<style scoped>.pagination { margin-top: 16px; justify-content: flex-end; }</style>
