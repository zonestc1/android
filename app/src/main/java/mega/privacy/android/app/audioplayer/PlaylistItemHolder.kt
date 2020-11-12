package mega.privacy.android.app.audioplayer

import mega.privacy.android.app.databinding.ItemPlaylistBinding

class PlaylistItemHolder(private val binding: ItemPlaylistBinding) :
    PlaylistViewHolder(binding) {
    override fun bind(item: PlaylistItem, itemOperation: PlaylistItemOperation) {
        binding.item = item
        binding.highlight = item.type == PlaylistItem.TYPE_NEXT
        binding.itemOperation = itemOperation
    }
}
