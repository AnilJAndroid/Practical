package com.example.mypractical.adapter

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mypractical.OnClickedListner
import com.example.mypractical.R
import com.example.mypractical.model.DepartmentModel

import kotlinx.android.synthetic.main.item_department.view.*
import kotlinx.android.synthetic.main.item_header.view.*

class DepartmentAdapter(var models: ArrayList<Any>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var onClickedListner: OnClickedListner? = null
    fun setClickListner(onClickListner: OnClickedListner) {
        this.onClickedListner = onClickListner
    }


    companion object {
        const val ITEMVIEW = 1
        const val HEADERVIEW = 2
    }

    inner class ItemViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview),
        View.OnClickListener {
        override fun onClick(view: View) {
            when (view.id) {
                view.rootView.tv_remove.id -> {
                    onClickedListner?.OnCLicked(adapterPosition, "remove")
                }
                view.rootView.sw_change_status.id -> {
                    onClickedListner?.OnCLicked(adapterPosition, "status")
                }
            }
        }

        init {
            itemview.rootView.tv_remove.setOnClickListener(this)
            itemview.rootView.sw_change_status.setOnClickListener(this)
        }


    }

    inner class HeaderViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview),
        View.OnClickListener {
        override fun onClick(view: View) {
            when (view.id) {
                view.rootView.ib_add.id -> {
                    onClickedListner?.OnCLicked(adapterPosition, "add")
                }
            }

        }

        init {
            itemview.rootView.ib_add.setOnClickListener(this)
        }

    }

    override fun getItemCount(): Int = models.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            HEADERVIEW -> {
                val headerView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_header, parent, false)
                HeaderViewHolder(headerView)
            }
            else -> {
                val headerView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_department, parent, false)
                ItemViewHolder(headerView)
            }
        }


    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = models[position]
        if (getItemViewType(position) == HEADERVIEW) {
            (holder as HeaderViewHolder).itemView.tv_header_title.text = model.toString()
        } else {
            (holder as ItemViewHolder).itemView.edt_emailid.setText("Enter name")
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (models[position] is String) {
            return HEADERVIEW
        }
        return ITEMVIEW

    }


    fun addItemPos(i: Int, item: Any) {
        models.add(i, item)
        notifyItemInserted(i)
    }

    fun addModels(list: ArrayList<Any>) {
        models.addAll(list)
        notifyDataSetChanged()
    }

    fun addItem(item: Any) {
        if (!models.contains(item)) {
            models.add(item)
            notifyDataSetChanged()
        }
    }

    fun removeItem(i: Int) {

        val model = models[i] as DepartmentModel
        models.filter {
            it is DepartmentModel
        }.let {
            it.map {
                (it as DepartmentModel).priority
            }.let {
                it.filter {
                    it == model.priority
                }.let {
                    if (it.size == 1) {
                        models.remove(model)
                        models.removeAt(i - 1)

                    } else {
                        models.removeAt(i);
                    }
                    notifyDataSetChanged()
                }
            }
        }


    }

    fun changeStatus(pos: Int) {
        val model = models[pos] as DepartmentModel
        model.status = !model.status
        notifyItemChanged(pos)
    }

    fun getListModels(): ArrayList<Any> {
        return models
    }
}