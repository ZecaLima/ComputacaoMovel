package ipvc.cm.cm_aulas.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ipvc.cm.cm_aulas.R
import ipvc.cm.cm_aulas.models.Todo
import kotlinx.android.synthetic.main.todo_line.view.*

class TodoAdapter(
    private val todos: ArrayList<Todo>
): RecyclerView.Adapter<TodoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(
                    R.layout.todo_line,
                    parent,
                    false
                )
        )
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val currentTodo = todos[position]

        holder.apply {
            pais.text = currentTodo.pais
            capital.text = currentTodo.capital
            habitantes.text = currentTodo.numHabitantes.toString()
            soma.text = (currentTodo.districts + currentTodo.freguesias).toString()
            if(currentTodo.numHabitantes<200){
                tipo.text = "pequeno"
            }
            if (currentTodo.numHabitantes in 200..500){
                tipo.text = "medio"
            }
            if (currentTodo.numHabitantes>500){
                tipo.text= "alto"
            }
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }

    fun addTodo(newTodo: Todo){
        todos.add(newTodo)
        notifyDataSetChanged()
    }
}

class TodoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val pais = itemView.tvPais
    val capital = itemView.tvCapital
    val habitantes = itemView.tvHabitantes
    val tipo = itemView.tvTipo
    val soma = itemView.tvSoma
}


