package club.plus1.kotlinapi.viewadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import club.plus1.kotlinapi.R
import club.plus1.kotlinapi.model.User

class UserAdapter(var items: List<User>, val callback: Callback) : RecyclerView.Adapter<UserAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            = MainHolder(LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false))

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class MainHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val text = itemView.findViewById<TextView>(R.id.text)
        private val image = itemView.findViewById<ImageView>(R.id.image)

        fun bind(item: User) {
            text.text = item.login
            DownloadImageTask(image).execute(item.avatar_url)
            itemView.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) callback.onItemClicked(items[adapterPosition])
            }
        }
    }

    interface Callback {
        fun onItemClicked(item: User)
    }

}