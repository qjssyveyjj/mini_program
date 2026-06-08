<template>
  <div class="page-container">
    <div class="table-card">
      <div class="search-bar">
        <el-input v-model="keyword" placeholder="搜索用户昵称" clearable style="width: 240px" />
        <el-button type="primary" @click="loadData">搜索</el-button>
        <el-button type="success" @click="openDialog()">新增记录</el-button>
      </div>
      <el-table :data="tableData" stripe border>
        <el-table-column prop="id" label="ID" width="60" />
        <el-table-column prop="nickname" label="用户" width="100" />
        <el-table-column prop="weight" label="体重(kg)" width="90" />
        <el-table-column prop="bmi" label="BMI" width="70" />
        <el-table-column prop="systolic" label="收缩压" width="80" />
        <el-table-column prop="diastolic" label="舒张压" width="80" />
        <el-table-column prop="bloodSugar" label="血糖" width="70" />
        <el-table-column prop="heartRate" label="心率" width="70" />
        <el-table-column prop="recordDate" label="日期" width="110" />
        <el-table-column label="操作" width="160" fixed="right">
          <template #default="{ row }">
            <el-button type="primary" link @click="openDialog(row)">编辑</el-button>
            <el-button type="danger" link @click="handleDelete(row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination class="pagination" v-model:current-page="page" :page-size="size" :total="total" layout="total, prev, pager, next" @current-change="loadData" />
    </div>
    <el-dialog v-model="dialogVisible" :title="form.id ? '编辑' : '新增'" width="520px">
      <el-form :model="form" label-width="90px">
        <el-form-item label="用户ID"><el-input-number v-model="form.userId" :min="1" /></el-form-item>
        <el-form-item label="身高(cm)"><el-input-number v-model="form.height" :min="0" :precision="1" /></el-form-item>
        <el-form-item label="体重(kg)"><el-input-number v-model="form.weight" :min="0" :precision="1" /></el-form-item>
        <el-form-item label="收缩压"><el-input-number v-model="form.systolic" :min="0" /></el-form-item>
        <el-form-item label="舒张压"><el-input-number v-model="form.diastolic" :min="0" /></el-form-item>
        <el-form-item label="血糖"><el-input-number v-model="form.bloodSugar" :min="0" :precision="1" :step="0.1" /></el-form-item>
        <el-form-item label="心率"><el-input-number v-model="form.heartRate" :min="0" /></el-form-item>
        <el-form-item label="日期"><el-date-picker v-model="form.recordDate" type="date" value-format="YYYY-MM-DD" /></el-form-item>
        <el-form-item label="备注"><el-input v-model="form.remark" /></el-form-item>
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
  const res = await request.get('/health-record/page', { params: { keyword: keyword.value, page: page.value, size: size.value } })
  tableData.value = res.data.records
  total.value = res.data.total
}

function openDialog(row) {
  form.value = row ? { ...row } : { userId: 1, recordDate: new Date().toISOString().slice(0, 10) }
  dialogVisible.value = true
}

async function handleSave() {
  if (form.value.id) await request.put('/health-record', form.value)
  else await request.post('/health-record', form.value)
  ElMessage.success('保存成功')
  dialogVisible.value = false
  loadData()
}

async function handleDelete(id) {
  await ElMessageBox.confirm('确定删除？', '提示', { type: 'warning' })
  await request.delete(`/health-record/${id}`)
  ElMessage.success('删除成功')
  loadData()
}
</script>

<style scoped>
.pagination { margin-top: 16px; justify-content: flex-end; }
</style>
