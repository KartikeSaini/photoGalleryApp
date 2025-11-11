package com.example.photogalleryapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.photogalleryapp.databinding.ActivityMainBinding
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.io.File

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: ImageViewmodel
    private lateinit var adapter: ImageAdapter
    private val PICK_IMAGE_REQUEST = 100
    private val CAMERA_REQUEST = 101
    private var cameraImageUri: Uri? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(ImageViewmodel::class.java)

        adapter = ImageAdapter(emptyList()) { entity ->
            AlertDialog.Builder(this)
                .setTitle("Delete?")
                .setMessage("Delete this image from gallery?")
                .setPositiveButton("Delete") { _, _ -> viewModel.delete(entity.id) }
                .setNegativeButton("Cancel", null)
                .show()
        }

        binding.recycler.layoutManager = GridLayoutManager(this, 3)
        binding.recycler.adapter = adapter

        // FAB -> open gallery via Intent
        binding.fabAdd.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, PICK_IMAGE_REQUEST)
        }

        lifecycleScope.launch {
            viewModel.images.collect { list ->
                adapter.update(list)
                binding.tvEmpty.visibility =
                    if (list.isEmpty()) android.view.View.VISIBLE else android.view.View.GONE
            }
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK) {
            data?.data?.let { uri ->
                viewModel.addUri(uri.toString())
            }
        }

        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            cameraImageUri?.let { uri ->
                viewModel.addUri(uri.toString())
            }
        }
        binding.fabAdd.setOnClickListener {
            val options = arrayOf("Pick from Gallery", "Capture with Camera")
            AlertDialog.Builder(this)
                .setTitle("Add Image")
                .setItems(options) { _, which ->
                    if (which == 0) openGallery()
                    else openCamera()
                }
                .show()
        }

    }


    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    private fun openCamera() {
        val imageFile = File(
            getExternalFilesDir(Environment.DIRECTORY_PICTURES),
            "IMG_${System.currentTimeMillis()}.jpg"
        )
        cameraImageUri = FileProvider.getUriForFile(this, "com.example.photogalleryapp.provider", imageFile)

        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, cameraImageUri)
        startActivityForResult(intent, CAMERA_REQUEST)
    }

}