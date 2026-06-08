import { get, post } from '../../utils/request'

/**
 * AI智能客服页面
 */
Page({
  data: {
    messages: [] as any[],
    inputText: '',
    loading: false,
    scrollTo: '',
    quickQuestions: [
      '我每天应该喝多少水？',
      '如何科学减重？',
      '跑步后应该怎么拉伸？',
      '怎样改善睡眠质量？',
      'BMI正常范围是多少？'
    ]
  },

  userId: 0,

  onLoad() {
    const user = wx.getStorageSync('user')
    if (!user) { wx.redirectTo({ url: '/pages/login/login' }); return }
    this.userId = user.id
    this.loadHistory()
  },

  /** 加载对话历史 */
  async loadHistory() {
    try {
      const res: any = await get(`/ai/history/${this.userId}`)
      const history = res.data || []
      if (history.length === 0) {
        this.setData({
          messages: [{
            role: 'assistant',
            content: '你好！我是小康，你的AI健康顾问。有任何饮食、运动、睡眠或健康指标方面的问题，都可以问我哦~'
          }]
        })
      } else {
        this.setData({ messages: history })
        this.scrollToBottom()
      }
    } catch (e) {
      this.setData({
        messages: [{
          role: 'assistant',
          content: '你好！我是小康，你的AI健康顾问。有任何饮食、运动、睡眠或健康指标方面的问题，都可以问我哦~'
        }]
      })
    }
  },

  onInput(e: any) { this.setData({ inputText: e.detail.value }) },

  sendQuick(e: any) {
    this.setData({ inputText: e.currentTarget.dataset.text })
    this.sendMessage()
  },

  /** 发送消息给AI */
  async sendMessage() {
    const { inputText, loading } = this.data
    if (!inputText.trim() || loading) return

    const userMsg = { role: 'user', content: inputText.trim() }
    const messages = [...this.data.messages, userMsg]
    this.setData({ messages, inputText: '', loading: true })
    this.scrollToBottom()

    try {
      const res: any = await post('/ai/chat', { userId: this.userId, message: inputText.trim() })
      const assistantMsg = { role: 'assistant', content: res.data.reply }
      this.setData({ messages: [...messages, assistantMsg], loading: false })
      this.scrollToBottom()
    } catch (e) {
      this.setData({ loading: false })
    }
  },

  scrollToBottom() {
    const len = this.data.messages.length
    this.setData({ scrollTo: `msg-${len}` })
  }
})
