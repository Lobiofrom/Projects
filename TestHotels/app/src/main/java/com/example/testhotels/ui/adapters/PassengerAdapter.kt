package com.example.testhotels.ui.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testhotels.R
import com.example.testhotels.databinding.ItemPassengerBinding
import com.example.testhotels.entity.passenger.Passenger
import com.example.testhotels.entity.passenger.TextField

class PassengerAdapter(
    val onTextChange: (TextField, Int) -> Unit
) : ListAdapter<Passenger, MyViewHolder>(DiffUtilCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemPassengerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = getItem(position)

        holder.binding.text.text = currentItem.text
        holder.binding.touristCount.text = currentItem.touristCount.toString()
        holder.binding.name.editText?.setText(currentItem.name)
        holder.binding.surname.editText?.setText(currentItem.surname)
        holder.binding.birthdate.editText?.setText(currentItem.birthdate)
        holder.binding.nationality.editText?.setText(currentItem.nationality)
        holder.binding.passportN.editText?.setText(currentItem.passportNumber)
        holder.binding.passportTime.editText?.setText(currentItem.passportDate)

        holder.binding.dropdownMenu.setOnClickListener {
            if (holder.binding.customLayout.visibility == View.GONE) {
                holder.binding.customLayout.visibility = View.VISIBLE
                holder.binding.dropdownMenu.setImageResource(R.drawable.img_5)
            } else {
                holder.binding.customLayout.visibility = View.GONE
                holder.binding.dropdownMenu.setImageResource(R.drawable.img_6)
            }
        }
        with(holder.binding) {

            name.apply {
                if (currentItem.nameError) setBoxBackgroundColorResource(R.color.error)
                editText?.setText(currentItem.name)
                editText?.doAfterTextChanged { text ->
                    if (text.toString().isNotEmpty())
                        boxBackgroundColor = Color.parseColor("#F6F6F9")
                    TextField.NAME.property = text.toString()
                    onTextChange(TextField.NAME, holder.bindingAdapterPosition)
                }
            }

            surname.apply {
                if (currentItem.surnameError) setBoxBackgroundColorResource(R.color.error)
                editText?.setText(currentItem.surname)
                editText?.doAfterTextChanged { text ->
                    if (text.toString().isNotEmpty())
                        boxBackgroundColor = Color.parseColor("#F6F6F9")
                    TextField.SURNAME.property = text.toString()
                    onTextChange(TextField.SURNAME, holder.bindingAdapterPosition)
                }
            }

            nationality.apply {
                if (currentItem.nationalityError) setBoxBackgroundColorResource(R.color.error)
                editText?.setText(currentItem.nationality)
                editText?.doAfterTextChanged { text ->
                    if (text.toString().isNotEmpty())
                        boxBackgroundColor = Color.parseColor("#F6F6F9")
                    TextField.NATIONALITY.property = text.toString()
                    onTextChange(TextField.NATIONALITY, holder.bindingAdapterPosition)
                }
            }

            birthdate.apply {
                if (currentItem.birthdayError) setBoxBackgroundColorResource(R.color.error)
                editText?.setText(currentItem.birthdate)
                editText?.doAfterTextChanged { text ->
                    if (text.toString().isNotEmpty())
                        boxBackgroundColor = Color.parseColor("#F6F6F9")
                    TextField.BIRTHDAY.property = text.toString()
                    onTextChange(TextField.BIRTHDAY, holder.bindingAdapterPosition)
                }
            }

            passportN.apply {
                if (currentItem.foreignPassportNumberError) setBoxBackgroundColorResource(R.color.error)
                editText?.setText(currentItem.passportNumber)
                editText?.doAfterTextChanged { text ->
                    if (text.toString().isNotEmpty())
                        boxBackgroundColor = Color.parseColor("#F6F6F9")
                    TextField.PASSPORT_NUMBER.property = text.toString()
                    onTextChange(TextField.PASSPORT_NUMBER, holder.bindingAdapterPosition)
                }
            }

            passportTime.apply {
                if (currentItem.foreignPassportDateError) setBoxBackgroundColorResource(R.color.error)
                editText?.setText(currentItem.passportDate)
                editText?.doAfterTextChanged { text ->
                    if (text.toString().isNotEmpty())
                        boxBackgroundColor = Color.parseColor("#F6F6F9")
                    TextField.PASSPORT_DATE.property = text.toString()
                    onTextChange(TextField.PASSPORT_DATE, holder.bindingAdapterPosition)
                }
            }

        }
    }
}

class DiffUtilCallback : DiffUtil.ItemCallback<Passenger>() {
    override fun areItemsTheSame(oldItem: Passenger, newItem: Passenger): Boolean =
        oldItem.touristCount == newItem.touristCount

    override fun areContentsTheSame(oldItem: Passenger, newItem: Passenger): Boolean {
        return oldItem.touristCount == newItem.touristCount &&
                oldItem.nameError == newItem.nameError &&
                oldItem.surnameError == newItem.surnameError
                && oldItem.birthdayError == newItem.birthdayError &&
                oldItem.nationalityError == newItem.nationalityError
                && oldItem.foreignPassportNumberError == newItem.foreignPassportNumberError
                && oldItem.foreignPassportDateError == newItem.foreignPassportDateError
    }
}

class MyViewHolder(val binding: ItemPassengerBinding) : RecyclerView.ViewHolder(binding.root)
