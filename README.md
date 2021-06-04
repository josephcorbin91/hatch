Original Chat Adapter Class I created an app that performs the same functionality of the adapter
public class ChatAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private static final int CONNECTION_ROW = 0;
    private static final int CONNECTION_HEADER = 1;

    private List<String> profileIds;
    private DatabaseManager database;

    public static class ChatViewHolder extends RecyclerView.ViewHolder {
        ImageView avatarImage;
        TextView name;
        TextView lastMessageSent;

        public ProfileViewHolder(View view) {
            super(view);

            avatarImage = (TextView) view.findViewById(R.id.avatar_image);
            name = (TextView) view.findViewById(R.id.name);
            lastMessageSent = (TextView) view.findViewById(R.id.last_message_sent);
        }
    }

    public static class ChatHeaderViewHolder extends RecyclerView.ViewHolder {
        TextView header;

        public ProfileViewHolder(View view) {
            super(view);
            header = (TextView) view.findViewById(R.id.header);
        }
    }

    public ChatAdapter(List<String> profileIds, DatabaseManager database) {
        this.profileIds = profileIds;
        this.database = database;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        switch(viewType) {
            case CONNECTION_ROW:
                View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.chat_row_view, viewGroup, false);

                return new ChatViewHolder(view);

            case CONNECTION_HEADER:
                View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.chat_header_view, viewGroup, false);

                return new ChatHeaderViewHolder(view);
        }

    }

// We are missing the the getItemViewType that returns a different view type based on whether or not it is a header/regular.
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // We should not be doing database operations in the onBindViewHolder because this task could be async and slow down performance
        // Type in databaes
        Profile profile = databaes.getProfile(profileIds.get(position));

        viewHolder.avatarImage.loadImage(profile.getPhotoUrl());
        viewHolder.name.setText(profile.getName);
        viewHolder.lastMessageSent.setText(profile.getLastMessageSent());
    }

    @Override
    public int getItemCount() {
        return profileIds.size();
    }
}