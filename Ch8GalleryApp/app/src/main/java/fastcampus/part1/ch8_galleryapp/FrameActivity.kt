package fastcampus.part1.ch8_galleryapp

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import fastcampus.part1.ch8_galleryapp.databinding.ActivityFrameBinding

class FrameActivity : AppCompatActivity() {
    private lateinit var binding : ActivityFrameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFrameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.apply {
            title = "나만의 앨범"
            setSupportActionBar(this)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        val images = (intent.getStringArrayExtra("images") ?: emptyArray())
            .map { uriString -> FrameItem(Uri.parse(uriString)) }.toList()
        val frameAdapter = FrameAdapter(images)
        binding.viewPager.adapter = frameAdapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) {
                tab, _ ->
            binding.viewPager.currentItem = tab.position
        }.attach()
    }
}