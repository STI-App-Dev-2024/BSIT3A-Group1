package com.example.aniwhere.adapter;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.aniwhere.R;
import com.example.aniwhere.models.ReportFoundPetModel;
import com.example.aniwhere.models.ReportLostPetModel;
import com.example.aniwhere.uiPage.LostAndFoundPageActivity;
import com.example.aniwhere.uiPage.ReportFoundPetPageActivity;

import java.util.List;

public class ReportFoundPetAdapter extends RecyclerView.Adapter<ReportFoundPetAdapter.ViewHolder>{
    private List<ReportFoundPetModel> foundPetList;
    private Context context;

    public ReportFoundPetAdapter(List<ReportFoundPetModel> foundPetList, Context context) {
        this.foundPetList = foundPetList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.foundbtnpage, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int currentPosition = holder.getAdapterPosition();
        if (currentPosition != RecyclerView.NO_POSITION) {
            ReportFoundPetModel foundPet = foundPetList.get(currentPosition);

            holder.nameEditText.setText(foundPet.getName());
            holder.breedEditText.setText(foundPet.getBreed());
            holder.genderEditText.setText(foundPet.getGender());
            holder.missingSinceButton.setText(foundPet.getMissingSince());
            holder.lastSeenEditText.setText(foundPet.getLastSeenLocation());
            holder.contactEditText.setText(foundPet.getContactNumber());
            holder.noteEditText.setText(foundPet.getNote());
            holder.postButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Ensure getAdapterPosition() is used again to get the current position
                    int currentPostion = holder.getAdapterPosition();

                    // Avoid performing an action if the item has been recycled
                    if (currentPostion != RecyclerView.NO_POSITION) {
                        Toast.makeText(context, "Post Successful for " + foundPet.getName(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(context, LostAndFoundPageActivity.class);
                        context.startActivity(intent);  // Start the LostAndFoundPageActivity
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return foundPetList.size();
    }
    public void updateList(List<ReportFoundPetModel> newList) {
        foundPetList = newList;
        notifyDataSetChanged();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        EditText nameEditText, breedEditText, genderEditText, lastSeenEditText, contactEditText, noteEditText;
        Button missingSinceButton, postButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.name);
            nameEditText = itemView.findViewById(R.id.firstname);
            breedEditText = itemView.findViewById(R.id.breed);
            genderEditText = itemView.findViewById(R.id.sex);
            missingSinceButton = itemView.findViewById(R.id.missing_since);
            lastSeenEditText = itemView.findViewById(R.id.lastseen);
            contactEditText = itemView.findViewById(R.id.contact);
            noteEditText = itemView.findViewById(R.id.note);
            postButton = itemView.findViewById(R.id.postbtn);

        }

    }
}