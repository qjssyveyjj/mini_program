<template>
  <div class="page-container">
    <div class="table-card">
      <div class="search-bar">
        <el-input v-model="keyword" placeholder="搜索用户名/昵称" clearable style="width: 240px" @clear="loadData" />
        <el-button type="primary" @click="loadData">搜索</el-button>
        <el-button type="success" @click="openDialog()">新增用户</el-button>
      </div>
      <el-table :data="tableData" stripe border>
        <el-table-column prop="id" label="ID" width="60" />
        <el-table-column prop="username" label="用户名" />
        <el-table-column prop="nickname" label="昵称" />
        <el-table-column prop="gender" label="性别" width="80">
          <template #default="{ row }">{{ ['未知','男','女'][row.gender] || '未知' }}</template>
        </el-table-column>
        <el-table-column prop="phone" label="手机号" />
        <el-table-column prop="height" label="身高(cm)" width="100" />
        <el-table-column prop="status" label="状态" width="80">
          <template #default="{ row }">
            <el-tag :type="row.status === 1 ? 'success' : 'danger'">{{ row.status === 1 ? '正常' : '禁用' }}</el-tag>
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
    <el-dialog v-model="dialogVisible" :title="form.id ? '编辑用户' : '新增用户'" width="500px">
      <el-form :model="form" label-width="80px">
        <el-form-item label="用户名"><el-input v-model="form.username" :disabled="!!form.id" /></el-form-item>
        <el-form-item label="昵称"><el-input v-model="form.nickname" /></el-form-item>
        <el-form-item label="性别">
          <el-select v-model="form.gender"><el-option :value="0" label="未知" /><el-option :value="1" label="男" /><el-option :value="2" label="女" /></el-select>
        </el-form-item>
        <el-form-item label="手机号"><el-input v-model="form.phone" /></el-form-item>
        <el-form-item label="身高"><el-input-number v-model="form.height" :min="0" :precision="1" /></el-form-item>
        <el-form-item label="目标体重"><el-input-number v-model="form.targetWeight" :min="0" :precision="1" /></el-form-item>
        <el-form-item label="状态">
          <el-switch v-model="form.status" :active-value="1" :inactive-value="0" />
        </el-form-item>
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

const keyword = ref('')
const page = ref(1)
const size = ref(10)
const total = ref(0)
const tableData = ref([])
const dialogVisible = ref(false)
const form = ref({})

onMounted(() => loadData())

async function loadData() {
  const res = await request.get('/user/page', { params: { keyword: keyword.value, page: page.value, size: size.value } })
  tableData.value = res.data.records
  total.value = res.data.total
}

function openDialog(row) {
  form.value = row ? { ...row } : { gender: 0, status: 1 }
  dialogVisible.value = true
}

async function handleSave() {
  if (form.value.id) {
    await request.put('/user', form.value)
  } else {
    await request.post('/user', form.value)
  }
  ElMessage.success('保存成功')
  dialogVisible.value = false
  loadData()
}

async function handleDelete(id) {
  await ElMessageBox.confirm('确定删除该用户？', '提示', { type: 'warning' })
  await request.delete(`/user/${id}`)
  ElMessage.success('删除成功')
  loadData()
}
</script>

<style scoped>
.pagination { margin-top: 16px; justify-content: flex-end; }
</style>
