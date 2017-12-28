# BaseRefresh-Kotlin
Kotlin编写刷新列表

首先导入库（使写起来更方便）

compile 'org.jetbrains.anko:anko-sdk15:0.9.1'


可以直接下面方式写：

	//mTvContent直接是xml中的id

	mTvContent.text = "Hello-World"

    mBtnJump2Main.text = "点击进入"


    打开一个Activity 并传递参数：

    	startActivity<FoundActivity>("key" to "from mainactivity")

    设置点击事件

    	 mBtnJump2Main.onClick {
    	 	//函数式编程 lamda
            Toast.makeText(this, "点击进入了", Toast.LENGTH_LONG).show()
            startActivity<FoundActivity>("key" to "from mainactivity")
        }



封装BaseViewHolder：

	open class BaseViewHolder(item: View?) : RecyclerView.ViewHolder(item) {

    private lateinit var mContext: Context
    private lateinit var mContentView: View
    private lateinit var mCacheViews: SparseArray<View>

    constructor(context: Context, item: View?) : this(item) {
        mContext = context
        mContentView = item ?: View(context)
        mCacheViews = SparseArray()
    }

    /**
     * 根据viewID获取对应的View
     */
    fun <T : View> retrieveView(@IdRes viewID: Int): T {
        var view = mCacheViews.get(viewID)

        if (null == view) {
            view = mContentView.find(viewID)
            mCacheViews.put(viewID, view)
        }
        return view as T
     }
    }



封装BaseAdapter（可添加footer header）

	
	getItemViewType：


	    override fun getItemViewType(position: Int): Int {
	        var count: Int = mDatas.size
	        if (hasHeader()) {
	            count++
	        }
	        //表达式
	        return if (hasHeader() && 0 == position) {
	            BaseAdapter.ITEM_TYPE.HEADER.ordinal
	        } else if (hasFooter() && position == count) {
	            BaseAdapter.ITEM_TYPE.FOOTER.ordinal
	        } else {
	            BaseAdapter.ITEM_TYPE.NORMAL.ordinal
	        }
	    }


	onCreateViewHolder：


	    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseViewHolder =
	            when (viewType) {
	                BaseAdapter.ITEM_TYPE.HEADER.ordinal -> BaseViewHolder(mContext, mHeaderView)
	                BaseAdapter.ITEM_TYPE.FOOTER.ordinal -> BaseViewHolder(mContext, mFooterView)
	                else -> BaseViewHolder(mContext, LayoutInflater.from(mContext).inflate(mLayoutID, parent, false))
	            }


	onBindViewHolder：


	    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
	        if (hasHeader() && 0 == position) {
	            return
	        }
	        var count = mDatas.size
	        if (hasHeader()) {
	            count++
	        }
	        if (hasFooter()) {
	            count++
	        }
	        if (hasFooter() && position == count - 1) {
	            return
	        }
	        if (hasHeader()) {
	            onChildBindViewHolder(holder, getData(position - 1))
	        } else {
	            onChildBindViewHolder(holder, getData(position))
	        }
	    }


一系列封装后，常用的列表Adapter中所有的代码：

	
	class RvListAdapter(private val mContext: Context, private val mLayout: Int, private val mDatas: List<ListBean>) : BaseAdapter<ListBean>(mContext, mLayout, mDatas) {

    constructor(context: Context, listData: ArrayList<ListBean>) : this(context, R.layout.item_list, listData)


    override fun onChildBindViewHolder(holder: BaseViewHolder, data: ListBean?) {
        //方式1 - 使用base中的方法获取View
	//        holder.retrieveView<TextView>(R.id.mTvName).text = data?.name ?: "chenxiaipie"
	//        holder.retrieveView<TextView>(R.id.mTvSex).text = data?.sex ?: "woman"
	//        holder.retrieveView<TextView>(R.id.mTvAge).text = data?.age?.toString() ?: ""

        //方式2
        holder.itemView.mTvName.text = data?.name ?: "chenxiaopie"
        holder.itemView.mTvSex.text = data?.sex ?: "woman"
        holder.itemView.mTvAge.text = data?.age?.toString() ?: ""
    }
}



以上是根据 https://github.com/wangchengmeng/RefreshRecyclerView 所封装下拉刷新列表使用kotlin简单的实现，熟悉一下kotlin的写法，kotlin还有更多实用简单的写法，仅供学习


代码地址：https://github.com/wangchengmeng/BaseRefresh-Kotlin