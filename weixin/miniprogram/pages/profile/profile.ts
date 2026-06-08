/**
 * 我的页面
 */
Page({
  data: {
    userInfo: {} as any,
    genderText: '未知'
  },

  onShow() {
    const user = wx.getStorageSync('user')
    if (!user) { wx.redirectTo({ url: '/pages/login/login' }); return }
    const genderMap: Record<number, string> = { 0: '未知', 1: '男', 2: '女' }
    this.setData({
      userInfo: user,
      genderText: genderMap[user.gender] || '未知'
    })
  },

  goReminder() { wx.navigateTo({ url: '/pages/reminder/reminder' }) },
  goAiChat() { wx.navigateTo({ url: '/pages/ai-chat/ai-chat' }) },

  handleLogout() {
    wx.showModal({
      title: '提示',
      content: '确定退出登录？',
      success(res) {
        if (res.confirm) {
          wx.removeStorageSync('user')
          getApp<IAppOption>().globalData.userInfo = null
          wx.redirectTo({ url: '/pages/login/login' })
        }
      }
    })
  }
})
