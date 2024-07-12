package com.phoenix.ecommerce.customers.products

import android.media.Rating
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.google.android.play.integrity.internal.i
import com.phoenix.ecommerce.data.data.product.Products
import com.phoenix.ecommerce.data.data.product.Review
import com.phoenix.ecommerce.utils.AddReviewStar
import com.phoenix.ecommerce.utils.ReviewStars
import com.phoenix.ecommerce.utils.SharedViewModel
import kotlin.math.roundToInt


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductReviewScreen(navController: NavController,sharedViewModel: SharedViewModel) {
    val context = LocalContext.current

    val products = sharedViewModel.product
    val viewModel : ProductsViewModel = viewModel()

    val addReviewDialogueState = remember {
        mutableStateOf(false)
    }

    val allReviews= viewModel.allReviews.observeAsState().value
    viewModel.getAllReviews(products)

    fun calculateAvgRating(allReviews: List<Review>?): Double {
        var tempRating = 0.0
        if (allReviews != null && allReviews.isNotEmpty()) {
            for (review in allReviews) {
                tempRating += review.rating
            }
            tempRating /= allReviews.size
        }
        return tempRating
    }


    Scaffold (

        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigateUp()
                    }) {
                        Icon(imageVector = Icons.AutoMirrored.Default.ArrowBack, contentDescription = "back")
                    }
                },
                title = {
                Text(text = products.productName)
            })
        },
        bottomBar = {
           Button(
               shape = RoundedCornerShape(5.dp),
               modifier = Modifier
                   .fillMaxWidth()
                   .padding(vertical = 30.dp, horizontal = 16.dp),
               onClick = {
                    addReviewDialogueState.value = true
           }) {
               Text(text = "Add Comment")
           }
        }
        
    ){


        Surface(modifier = Modifier.padding(it)) {

            if(addReviewDialogueState.value) {
                AddCommentDialogue(products = products, viewModel = viewModel){
                    if(!it ){
                        addReviewDialogueState.value = false
                    }
                }
            }



            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){

                    Text(
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        text = "Reviews"
                    )
                    Row {
                        Text(text = " Total (${allReviews?.size})")
                        ReviewStars(color = Color(0xffffa534), numberOfStars = calculateAvgRating(allReviews).roundToInt())

                    }
                }


                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.padding(vertical = 16.dp),

                    ) {

                    if(allReviews != null) {
                        items(allReviews) {
                            EachReview(it)
                        }
                    }
                }
                
            }

        }
    }
}

@Composable
fun EachReview(review: Review){

    val viewModel : ProductsViewModel = viewModel()
    val userName = remember {
        mutableStateOf("")
    }
    val profileImage = remember {
        mutableStateOf("")
    }

    viewModel.getUserImgAndName(review.userEmail){
        name,image ->
        userName.value = name
        profileImage.value = image
    }

    Card (
        colors = CardColors(
            containerColor =MaterialTheme.colorScheme.primaryContainer,
            contentColor =MaterialTheme.colorScheme.primary,
            disabledContainerColor=MaterialTheme.colorScheme.primaryContainer,
            disabledContentColor =MaterialTheme.colorScheme.primary
        ),
        modifier = Modifier.fillMaxWidth()
    ){
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ){
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween){
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                    Card(
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .wrapContentSize()

                    ) {
                        AsyncImage(
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.size(50.dp),
                            model = profileImage.value, contentDescription = "Profile Image"
                        )
                    }
                    Column {
                        Text(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            text = userName.value
                        )
                        ReviewStars(color = Color(0xffffa534), numberOfStars = review.rating)
                    }
                }

            }

            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = review.comment)



        }

    }
}


@Composable
fun AddCommentDialogue(products: Products,viewModel: ProductsViewModel, stateChange :(Boolean)->Unit) {

    val context = LocalContext.current

    val comment = remember {
        mutableStateOf("")
    }
    val rating = remember { mutableIntStateOf(1) }

    Dialog(
        onDismissRequest = {
            stateChange(false)

        }) {

        Box(modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                Text(
                    textDecoration = TextDecoration.Underline,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    text = "Add Review")
                AddReviewStar(rating = rating.intValue.toFloat()){
                    rating.intValue = it.toInt()
                }

                OutlinedTextField(
                    trailingIcon = {
                        IconButton(
                            onClick = {

                                viewModel.addReview(products, comment.value, rating.intValue)
                                stateChange(false)

                            }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Default.Send,
                                contentDescription = ""
                            )
                        }
                    },
                    placeholder = {
                        Text(text = "Enter your review here")
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = comment.value, onValueChange = {
                        comment.value = it
                    })
            }

        }
    }
}