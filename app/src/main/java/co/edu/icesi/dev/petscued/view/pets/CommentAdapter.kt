package co.edu.icesi.dev.petscued.view.pets

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.edu.icesi.dev.petscued.R
import co.edu.icesi.dev.petscued.model.Comment
import co.edu.icesi.dev.petscued.model.Publication
import co.edu.icesi.dev.petscued.model.User
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CommentAdapter (private val petInfoFragment: PetInfoFragment) : RecyclerView.Adapter<CommentView>() {

    private var commentList = ArrayList<Comment>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentView {
        val layoutInflater = LayoutInflater.from(parent.context)
        val comment = layoutInflater.inflate(R.layout.comment, parent, false)
        return CommentView(comment)
    }

    override fun onBindViewHolder(holder: CommentView, position: Int) {
        val comment = commentList[position]
        comment.also { holder.comment = it }

        Firebase.firestore.collection("users")
            .whereEqualTo("userId", comment.userId).get()
            .addOnCompleteListener { task ->
                for(doc in task.result!!){
                    val user = doc.toObject(User::class.java)
                    holder.nameUserTextView.text= user.name
                }
            }

        holder.commentTextView.text= comment.content
    }

    override fun getItemCount(): Int {
        return commentList.size
    }

    fun addComment(comment: Comment) {
        commentList.add(comment)
        notifyItemInserted(commentList.size - 1)
    }
}