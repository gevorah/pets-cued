package co.edu.icesi.dev.petscued.view.pets

import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import co.edu.icesi.dev.petscued.R
import co.edu.icesi.dev.petscued.model.Comment


class CommentView (itemView: View) : RecyclerView.ViewHolder(itemView) {

    //State
    var comment : Comment?=null

    var commentTextView : TextView = itemView.findViewById(R.id.commentTextView)
    var nameUserTextView : TextView = itemView.findViewById(R.id.nameUserTextView)
}