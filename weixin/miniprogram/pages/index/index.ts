import { get } from '../../utils/request'

/**
 * 首页 - 今日健康概览
 */
Page({
  data: {
    userInfo: {} as any,
    todayHealth: {} as any,
    todayCalories: 0,
    todayExercise: 0,
    articles: [] as any[]
  },

  onShow() {
    const user = wx.getStorageSync('user')
    if (!user) {
      wx.redirectTo({ url: '/pages/login/login' })
      return
    }
    this.setData({ userInfo: user })
    this.loadData(user.id)
  },

  /** 加载首页数据 */
  async loadData(userId: number) {
    const today = new Date().toISOString().slice(0, 10)
    try {
      const [healthRes, dietRes, exerciseRes, articleRes]: any[] = await Promise.all([
        get('/health-record/page', { userId, page: 1, size: 1 }),
        get('/diet/page', { userId, page: 1, size: 50 }),
        get('/exercise/page', { userId, page: 1, size: 50 }),
        get('/article/page', { status: 1, page: 1, size: 3 })
      ])

      const healthRecords = healthRes.data.records || []
      const dietRecords = (dietRes.data.records || []).filter((d: any) => d.recordDate === today)
      const exerciseRecords = (exerciseRes.data.records || []).filter((e: any) => e.recordDate === today)

      this.setData({
        todayHealth: healthRecords[0] || {},
        todayCalories: dietRecords.reduce((sum: number, d: any) => sum + (d.calories || 0), 0),
        todayExercise: exerciseRecords.reduce((sum: number, e: any) => sum + (e.duration || 0), 0),
        articles: articleRes.data.records || []
      })
    } catch (e) { /* handled */ }
  },

  goHealth(e: any) {
    wx.switchTab({ url: '/pages/health/health' })
  },

  goReminder() {
    wx.navigateTo({ url: '/pages/reminder/reminder' })
  },

  goAiChat() {
    wx.navigateTo({ url: '/pages/ai-chat/ai-chat' })
  },

  goArticleDetail(e: any) {
    wx.navigateTo({ url: `/pages/article-detail/article-detail?id=${e.currentTarget.dataset.id}` })
  }
})
