package fastcampus.part1.ch8_galleryapp

import android.Manifest.permission.READ_MEDIA_IMAGES
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import fastcampus.part1.ch8_galleryapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val imageLoadLauncher = registerForActivityResult(ActivityResultContracts.GetMultipleContents()) { uriList ->
        updateImages(uriList)
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var imageAdapter : ImageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.apply {
            title = "사진 가져오기"
            setSupportActionBar(this)
        }

        binding.loadImageButton.setOnClickListener {
            checkPermission()
        }

        binding.toFrameActivityButton.setOnClickListener {
            toFrameActivity()
        }

        initRecyclerView()
    }

    // connect Adapter to RecyclerView
    private fun initRecyclerView() {
        imageAdapter = ImageAdapter(object : ImageAdapter.ItemClickListener {
            override fun onLoadMoreClick() {
                checkPermission()
            }
        })

        binding.imageRecyclerView.apply {
            adapter = imageAdapter
            layoutManager = GridLayoutManager(context, 2)
        }
    }

    private fun checkPermission() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                READ_MEDIA_IMAGES
            ) == PackageManager.PERMISSION_GRANTED -> {
                loadImage()
            }

            shouldShowRequestPermissionRationale(READ_MEDIA_IMAGES) -> {
                showPermissionInfoDialog()
            }

            else -> {
                requestReadMediaImages()
            }
        }
    }

    private fun loadImage() {
        imageLoadLauncher.launch("image/*")
    }

    private fun showPermissionInfoDialog() {
        AlertDialog.Builder(this).apply {
            setMessage("이미지를 가져오기 위해 외부 저장소 읽기 권한이 필요합니다.")
            setNegativeButton("Cancel", null)
            setPositiveButton("OK") { _, _ ->
                requestReadMediaImages()
            }
        }.show()
    }

    private fun requestReadMediaImages() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(READ_MEDIA_IMAGES),
            REQUEST_READ_MEDIA_IMAGES
        )
    }

    private fun updateImages(uriList: List<Uri>) {
        Log.d("updateImages", "$uriList")
        val images = uriList.map { ImageItems.Image(it) }
        val updatedImages = imageAdapter.currentList.toMutableList().apply { addAll(images) }   // add extra images on existing images
        imageAdapter.submitList(updatedImages)
    }

    // As soon as you grant app permission, image loading screen appears
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when(requestCode) {
            REQUEST_READ_MEDIA_IMAGES -> {
                val resultCode = grantResults.firstOrNull() ?: PackageManager.PERMISSION_DENIED
                if(resultCode == PackageManager.PERMISSION_GRANTED) {
                    loadImage()
                }
            }
        }
    }

    private fun toFrameActivity() {
        val images = imageAdapter.currentList.filterIsInstance<ImageItems.Image>().map { it.uri.toString() }.toTypedArray()
        val intent = Intent(this, FrameActivity::class.java)
            .putExtra("images", images)
        startActivity(intent)
    }

    companion object {
        const val REQUEST_READ_MEDIA_IMAGES = 100
    }
}