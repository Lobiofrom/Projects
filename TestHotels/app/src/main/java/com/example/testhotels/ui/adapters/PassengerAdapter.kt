package com.example.testhotels.ui.adapters

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.example.testhotels.R
import com.example.testhotels.databinding.ItemPassengerBinding
import com.example.testhotels.entity.passenger.Passenger

class PassengerAdapter(
    var passengerList: List<Passenger>
) : RecyclerView.Adapter<MyViewHolder>() {

    var isAllValid = false
        private set

    val passengerStatesList = MutableList(passengerList.size) {
        PassengerStates()
    }

    fun addItem(passenger: Passenger) {
        val updatedList = passengerList.toMutableList()
        updatedList.add(passenger)
        passengerList = updatedList
        passengerStatesList.add(PassengerStates())
        notifyItemInserted(passengerList.size - 1)
    }

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
        val currentItem = passengerList[position]
        val passengerStates = passengerStatesList[position]

        holder.binding.text.text = currentItem.text
        holder.binding.touristCount.text = currentItem.tourist_count.toString()
        holder.binding.name.text = currentItem.name
        holder.binding.surname.text = currentItem.surname
        holder.binding.birthdate.text = currentItem.birthdate
        holder.binding.nationality.text = currentItem.nationality
        holder.binding.passportN.text = currentItem.passportN
        holder.binding.passportTime.text = currentItem.passportTime

        holder.binding.dropdownMenu.setOnClickListener {
            if (holder.binding.customLayout.visibility == View.GONE) {
                holder.binding.customLayout.visibility = View.VISIBLE
                holder.binding.dropdownMenu.setImageResource(R.drawable.img_5)
            } else {
                holder.binding.customLayout.visibility = View.GONE
                holder.binding.dropdownMenu.setImageResource(R.drawable.img_6)
            }
        }

        holder.binding.name.doOnTextChanged { text, _, _, _ ->
            passengerStates.isNameValid = !text.isNullOrEmpty()
            updateIsAllValid()

            if (text.isNullOrEmpty()) {
                holder.binding.customtextInputLayout1.isErrorEnabled = true
                holder.binding.customtextInputLayout1.error = "заполните поле"
            } else {
                holder.binding.customtextInputLayout1.isErrorEnabled = false
                holder.binding.name.backgroundTintList = null
            }
        }

        holder.binding.surname.doOnTextChanged { text, _, _, _ ->
            passengerStates.isSurnameValid = !text.isNullOrEmpty()
            updateIsAllValid()

            if (text.isNullOrEmpty()) {
                holder.binding.customtextInputLayout2.isErrorEnabled = true
                holder.binding.customtextInputLayout2.error = "заполните поле"
            } else {
                holder.binding.customtextInputLayout2.isErrorEnabled = false
                holder.binding.surname.backgroundTintList = null
            }
        }

        holder.binding.birthdate.doOnTextChanged { text, _, _, _ ->
            passengerStates.isBirthdateValid = !text.isNullOrEmpty()
            updateIsAllValid()

            if (text.isNullOrEmpty()) {
                holder.binding.customtextInputLayout3.isErrorEnabled = true
                holder.binding.customtextInputLayout3.error = "заполните поле"
            } else {
                holder.binding.customtextInputLayout3.isErrorEnabled = false
                holder.binding.birthdate.backgroundTintList = null
            }
        }
        holder.binding.nationality.doOnTextChanged { text, _, _, _ ->
            passengerStates.isNationalityValid = !text.isNullOrEmpty()
            updateIsAllValid()

            if (text.isNullOrEmpty()) {
                holder.binding.customtextInputLayout4.isErrorEnabled = true
                holder.binding.customtextInputLayout4.error = "заполните поле"
            } else {
                holder.binding.customtextInputLayout4.isErrorEnabled = false
                holder.binding.nationality.backgroundTintList = null
            }
        }
        holder.binding.passportN.doOnTextChanged { text, _, _, _ ->
            passengerStates.isPassportNValid = !text.isNullOrEmpty()
            updateIsAllValid()

            if (text.isNullOrEmpty()) {
                holder.binding.customtextInputLayout5.isErrorEnabled = true
                holder.binding.customtextInputLayout5.error = "заполните поле"
            } else {
                holder.binding.customtextInputLayout5.isErrorEnabled = false
                holder.binding.passportN.backgroundTintList = null
            }
        }
        holder.binding.passportTime.doOnTextChanged { text, _, _, _ ->
            passengerStates.isPassportTimeValid = !text.isNullOrEmpty()
            updateIsAllValid()

            if (text.isNullOrEmpty()) {
                holder.binding.customtextInputLayout6.isErrorEnabled = true
                holder.binding.customtextInputLayout6.error = "заполните поле"
            } else {
                holder.binding.customtextInputLayout6.isErrorEnabled = false
                holder.binding.passportTime.backgroundTintList = null
            }
        }
    }

    fun highlightEmptyFields(recyclerView: RecyclerView) {
        passengerStatesList.forEachIndexed { index, passengerStates ->
            if (!passengerStates.isAllValid()) {
                val holder = recyclerView.findViewHolderForAdapterPosition(index) as MyViewHolder?
                holder?.binding?.name?.let { editText ->
                    if (editText.text.isNullOrEmpty()) {
                        editText.backgroundTintList =
                            ColorStateList.valueOf(Color.parseColor("#EB5757"))
                    } else {
                        editText.backgroundTintList = null

                    }
                }
                holder?.binding?.surname?.let { editText ->
                    if (editText.text.isNullOrEmpty()) {
                        editText.backgroundTintList =
                            ColorStateList.valueOf(Color.parseColor("#EB5757"))
                    } else {
                        editText.backgroundTintList = null

                    }
                }
                holder?.binding?.nationality?.let { editText ->
                    if (editText.text.isNullOrEmpty()) {
                        editText.backgroundTintList =
                            ColorStateList.valueOf(Color.parseColor("#EB5757"))
                    } else {
                        editText.backgroundTintList = null

                    }
                }
                holder?.binding?.passportTime?.let { editText ->
                    if (editText.text.isNullOrEmpty()) {
                        editText.backgroundTintList =
                            ColorStateList.valueOf(Color.parseColor("#EB5757"))
                    } else {
                        editText.backgroundTintList = null

                    }
                }
                holder?.binding?.passportN?.let { editText ->
                    if (editText.text.isNullOrEmpty()) {
                        editText.backgroundTintList =
                            ColorStateList.valueOf(Color.parseColor("#EB5757"))
                    } else {
                        editText.backgroundTintList = null

                    }
                }
                holder?.binding?.birthdate?.let { editText ->
                    if (editText.text.isNullOrEmpty()) {
                        editText.backgroundTintList =
                            ColorStateList.valueOf(Color.parseColor("#EB5757"))
                    } else {
                        editText.backgroundTintList = null

                    }
                }
            }
        }
    }


    private fun updateIsAllValid() {
        isAllValid = passengerStatesList.all { it.isAllValid() }
    }

    override fun getItemCount() = passengerList.size

}

class PassengerStates(
    var isNameValid: Boolean = false,
    var isSurnameValid: Boolean = false,
    var isBirthdateValid: Boolean = false,
    var isNationalityValid: Boolean = false,
    var isPassportNValid: Boolean = false,
    var isPassportTimeValid: Boolean = false
) {
    fun isAllValid() = isNameValid && isSurnameValid && isBirthdateValid &&
            isNationalityValid && isPassportNValid && isPassportTimeValid
}

class MyViewHolder(val binding: ItemPassengerBinding) : RecyclerView.ViewHolder(binding.root)

