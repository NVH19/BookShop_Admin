package com.example.bookshop_admin.ui.main.book

import android.app.AlertDialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bookshop_admin.R
import com.example.bookshop_admin.data.model.Product
import com.example.bookshop_admin.databinding.FragmentBookBinding
import com.example.bookshop_admin.ui.adapter.BookAdapter
import com.example.bookshop_admin.ui.adapter.OnItemClickListener
import com.example.bookshop_admin.ui.adapter.OnItemLongClickListener
import com.example.bookshop_admin.ui.main.book.addbook.AddBookFragment
import com.example.bookshop_admin.utils.ItemSpacingDecoration

class BookFragment : Fragment() {

    private lateinit var viewModel: BookViewModel
    private var binding: FragmentBookBinding? = null
    private lateinit var adapter: BookAdapter
    private var bookList = mutableListOf<Product>()
    private var currentPage = 1
    private var lastPosition = 0
    private var totalPosition = 0
    private var currentPosition = 0
    private var pastPage = -1
    private var loadData: Boolean = true
    private val searchHandler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[BookViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBookBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = BookAdapter()
        initViewModel()
        viewModel.getProducts(10, currentPage, 5)
        handleSearch()
        val horizontalSpacing =
            resources.getDimensionPixelSize(R.dimen.horizontal_spacing)
        val verticalSpacing =
            resources.getDimensionPixelSize(R.dimen.vertical_spacing)
        binding?.apply {
            recyclerBook.addItemDecoration(
                ItemSpacingDecoration(
                    horizontalSpacing,
                    verticalSpacing
                )
            )
            val layoutManager = GridLayoutManager(context, 2)
            recyclerBook.layoutManager = layoutManager
            recyclerBook.adapter = adapter
            floatButton.setOnClickListener {
                val bundle = Bundle()
                bundle.putBoolean("isAddBook", true)
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, AddBookFragment().apply { arguments = bundle })
                    .addToBackStack("BookFragment").commit()
            }
            swipeRefresh.setOnRefreshListener {
                Handler().postDelayed({
                    swipeRefresh.isRefreshing = false
                    loadData = false
                    viewModel.getProducts(10, 1, 5)
                }, 1000)
            }
            swipeRefresh.setColorSchemeColors(resources.getColor(R.color.teal_200))
        }
        handleLoadData()
        navToEdittingScreen()
        alertDeleteBook()
    }

    private fun initViewModel() {
        viewModel.bookList.observe(viewLifecycleOwner) { state ->
            val isDefaultState = state.isDefaultState
            state.products?.let {
                if (it.isEmpty()) {
                    currentPage = 1
                } else {
                    if (isDefaultState) {
                        if (currentPage > 1 && loadData) {
                            bookList.addAll(it)
                        } else {
                            bookList.clear()
                            bookList.addAll(it)
                        }
                    } else {
                        bookList.clear()
                        bookList.addAll(it)
                    }
                }
                adapter.setData(bookList)
//                navToProductDetail()
//                addItemToCart()
                binding?.loadingLayout?.root?.visibility = View.INVISIBLE
            }
        }
    }


    private fun handleLoadData() {
        binding?.apply {
            recyclerBook.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    loadData = true
                    lastPosition =
                        (recyclerBook.layoutManager as GridLayoutManager).findLastVisibleItemPosition()
                    totalPosition = adapter.itemCount
                    if (lastPosition != currentPosition && ((lastPosition == totalPosition - 3 && totalPosition % 2 == 0) || (lastPosition == totalPosition - 2 && totalPosition % 2 != 0))) {
                        currentPage++
                        viewModel.getProducts(10, currentPage, 5)
                        currentPosition = lastPosition
                    }
                }
            })
        }
    }

    private fun handleSearch() {
        binding?.apply {
            searchProduct.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    // task HERE
                    return false
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    if (newText.isEmpty()) {
//                        currentPage = 1
                        loadData = false
                        viewModel.getProducts(10, 1, 5)
                        loadingLayout.root.visibility = View.VISIBLE
                    } else {
                        val delayMillis = 300L
                        searchHandler.removeCallbacksAndMessages(null)
                        searchHandler.postDelayed({
                            viewModel.getSearchProducts(10, 1, 5, newText)
                        }, delayMillis)
                    }
                    return false;
                }
            })
        }
    }

    private fun navToEdittingScreen() {
        adapter.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                val book = adapter.getBook(position)
                val bundle = Bundle()
                bundle.putString("bookId", book.product_id.toString())
                bundle.putBoolean("isAddBook", false)
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, AddBookFragment().apply { arguments = bundle })
                    .addToBackStack("BOOK").commit()
                pastPage = currentPage
            }

        })
    }

    private fun alertDeleteBook() {
        adapter.setOnItemLongClickListener(object : OnItemLongClickListener {
            override fun onItemLongClick(position: Int) {
                val book = adapter.getBook(position)
                AlertDialog.Builder(context)
                    .setTitle("Thông báo!")
                    .setIcon(R.drawable.ic_delete)
                    .setMessage("Bạn có chắc chắn muốn xóa sản phẩm '${book.name}' không?")
                    .setPositiveButton("OK") { dialog, _ ->
                        viewModel.deleteBook(book.product_id)
                        dialog.cancel()
                    }
                    .setNegativeButton("Cancel") { dialog, _ ->
                        dialog.cancel()
                    }
                    .show()
            }
        })
    }
}