import { createRouter, createWebHistory } from 'vue-router'

const routes = [
  {
    path: '/login',
    name: 'Login',
    component: () => import('../views/Login.vue')
  },
  {
    path: '/',
    component: () => import('../views/Layout.vue'),
    redirect: '/home',
    children: [
      { path: 'home', name: 'Home', component: () => import('../views/Home.vue'), meta: { title: '数据看板' } },
      { path: 'user', name: 'UserManage', component: () => import('../views/UserManage.vue'), meta: { title: '用户管理' } },
      { path: 'health-record', name: 'HealthRecordManage', component: () => import('../views/HealthRecordManage.vue'), meta: { title: '健康档案' } },
      { path: 'diet', name: 'DietManage', component: () => import('../views/DietManage.vue'), meta: { title: '饮食记录' } },
      { path: 'exercise', name: 'ExerciseManage', component: () => import('../views/ExerciseManage.vue'), meta: { title: '运动记录' } },
      { path: 'sleep', name: 'SleepManage', component: () => import('../views/SleepManage.vue'), meta: { title: '睡眠记录' } },
      { path: 'reminder', name: 'ReminderManage', component: () => import('../views/ReminderManage.vue'), meta: { title: '健康提醒' } },
      { path: 'article', name: 'ArticleManage', component: () => import('../views/ArticleManage.vue'), meta: { title: '健康资讯' } }
    ]
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

router.beforeEach((to, from, next) => {
  if (to.path !== '/login' && !localStorage.getItem('admin')) {
    next('/login')
  } else {
    next()
  }
})

export default router
