
package com.example.productfinderfromml.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.productfinderfromml.R
import com.example.productfinderfromml.data.model.Resultado

/**
 * View Holder for a [Repo] RecyclerView list item.
 */
class ResultadoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val name: TextView = view.findViewById(R.id.txt_titulo)
    private val description: TextView = view.findViewById(R.id.txt_descripcion)

    private var repo: Resultado? = null

    init {
        view.setOnClickListener {
            /*repo?.url?.let { url ->
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                view.context.startActivity(intent)
            }*/
        }
    }

    fun bind(repo: Resultado?) {
        if (repo == null) {
            val resources = itemView.resources
            name.text = "R.string.loading"
            description.visibility = View.GONE
            /*language.visibility = View.GONE
            stars.text = "R.string.unknown"
            forks.text = "R.string.unknown"*/
        } else {
            showRepoData(repo)
        }
    }

    private fun showRepoData(repo: Resultado) {
        this.repo = repo
        name.text = repo.title

        // if the description is missing, hide the TextView
        var descriptionVisibility = View.GONE
        /*if (repo.description != null) {
            description.text = repo.description
            descriptionVisibility = View.VISIBLE
        }*/
        description.visibility = descriptionVisibility

        /*stars.text = repo.stars.toString()
        forks.text = repo.forks.toString()

        // if the language is missing, hide the label and the value
        var languageVisibility = View.GONE
        if (!repo.language.isNullOrEmpty()) {
            val resources = this.itemView.context.resources
            language.text = resources.getString(R.string.language, repo.language)
            languageVisibility = View.VISIBLE
        }
        language.visibility = languageVisibility*/
    }

    companion object {
        fun create(parent: ViewGroup): ResultadoViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.tragos_row, parent, false)
            return ResultadoViewHolder(view)
        }
    }
}