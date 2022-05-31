package com.example.Figma.Adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.figma.models.Model
import com.example.figma.R

class Adapter(private val mList: ArrayList<Model>, val context: Context): RecyclerView.Adapter<Adapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

            val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.view, parent, false)

        return MyViewHolder(itemView)


    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.name.text = mList[position].name
        holder.email.text = mList[position].email
        holder.number.text = mList[position].phoneNumber
        holder.password.text = mList[position].password

        holder.message.setOnClickListener {

            Toast.makeText(context, "hello", Toast.LENGTH_SHORT).show()

            val smsIntent = Intent(Intent.ACTION_VIEW).also {
                it.putExtra(Intent.EXTRA_SUBJECT, holder.number.text.toString())
            }

            smsIntent.data = Uri.parse("smsto:${holder.number.text.toString()}")

            context.startActivity(smsIntent)
        }

//        holder.itemView.setOnLongClickListener{
//
//            val view = LayoutInflater.from(context).inflate(R.layout.change_name, null)
//
//            val dialog = AlertDialog.Builder(context)
//                .setView(view)
//                .create()
//
//            val button: Button = view.findViewById(R.id.change)
//
//            button.setOnClickListener {
//                val db = DBHelper(context, null)
//                db
//
//                db.updateName()
//
//            }
//
//            return@setOnLongClickListener true
//
//
//        }
    }

    override fun getItemCount(): Int {

        return mList.size

    }





    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        var name: TextView = itemView.findViewById(R.id.name)
        val email: TextView = itemView.findViewById(R.id.emailAddress)
        val number: TextView = itemView.findViewById(R.id.phoneNumber)
        val password: TextView = itemView.findViewById(R.id.password)
        val message: ImageView = itemView.findViewById(R.id.message)
//        public var foreground: ConstraintLayout = itemView.findViewById(R.id.foreground)
//        public var background: RelativeLayout = itemView.findViewById(R.id.background)



    }


}
