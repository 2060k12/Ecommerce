package com.phoenix.ecommerce.admin.ui

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.phoenix.ecommerce.admin.AdminViewModel
import com.phoenix.ecommerce.customers.products.ProductsViewModel
import com.phoenix.ecommerce.data.data.AdminReceivedOrder
import com.phoenix.ecommerce.data.data.product.Products
import com.phoenix.ecommerce.navigation.RoutesAdmin
import com.phoenix.ecommerce.utils.AddReviewStar
import androidx.compose.foundation.layout.Column as Column1


// card to add new product
@Composable
fun EditCardComponent(){
    Card(

        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .height(80.dp)
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .clickable {
                    // TODO: add navigation to edit product screen
                }
                .fillMaxSize()
                .padding(24.dp)){
            Text(
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                text = "Edit Product")
            Icon(
                modifier = Modifier.size(40.dp),
                imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight, contentDescription = "Arrow Right")

        }
    }
}


@Composable
fun galleryLauncher( callback : (imageLink: Uri)-> Unit): (() -> Unit){
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = {
                uri ->
            uri?.let {
                callback(it)
            }
        }
    )
    return { galleryLauncher.launch("image/*") }
}


@Composable
fun EachColorPickerCard(containerColor: Color,  colorName: String, onClick : () -> Unit ){
    var checkedState by remember { mutableStateOf(false)}
    var color = Color.White
    if(colorName.lowercase() == "white")  color = Color.Black
    var selectedChip = remember {

    mutableStateOf(true)}
    AssistChip(
        enabled = selectedChip.value,
        colors = AssistChipDefaults.assistChipColors(containerColor = containerColor, labelColor = color),
        onClick = {
            selectedChip.value = false
            onClick() },
        label = {
            Text(text = colorName)
        })
}


@Composable
fun ReceivedOrders(receivedOrder: AdminReceivedOrder, onClick: () -> Unit) {
    val expandableState = remember{ mutableStateOf(false) }
    val cardHeight by animateDpAsState(targetValue = if (expandableState.value) 310.dp else 100.dp)



    Card(
        onClick = {
                if(!expandableState.value) {
                    expandableState.value = true
                }
                else {
                    expandableState.value = false }
                  },
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
            .height(cardHeight)
    ) {

        Column1(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxSize()
        ) {



            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .weight(0.4f)
                    .fillMaxSize()
                    .padding(start = 10.dp)
            ) {

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Card(
                        modifier = Modifier
                            .size(80.dp)
                            .padding(),
                        shape = RoundedCornerShape(10.dp),
                    ) {
                        AsyncImage(
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(1.dp),
                            model = receivedOrder.products.productIconUrl,
                            contentDescription = ""
                        )
                    }
                    Column1(
                        modifier = Modifier.padding(horizontal = 8.dp)
                    ) {
                        Text(
                            fontWeight = FontWeight.Bold,
                            text = receivedOrder.products.productName
                        )
                        Text(
                            fontWeight = FontWeight.Bold,
                            text = receivedOrder.products.productCategory
                        )
                        Text(
                            text = "Received: ${
                                receivedOrder.orderPlacedOn.toInstant().toString().dropLast(14)
                            } "
                        )


                    }
                }

                Text(
                    fontWeight = FontWeight.Bold,
                    text = "AU$ ${receivedOrder.products.productCost.toString()}"
                )

            }

            if(expandableState.value) {
                Column1(
                    modifier = Modifier
                        .padding(8.dp)
                        .weight(0.8f)
                        .fillMaxSize()
                ){
                    Row {
                        Text(
                            fontSize = 14.sp,
                            text = "Deliver To: ${receivedOrder.deliverTo}")
                    }
                    Row {
                        Text(
                            fontSize = 14.sp,
                            text = "Contact Number: ${receivedOrder.contactNumber}")
                    }
                    Row {
                        Text(
                            fontSize = 14.sp,
                            text = "Address: ${receivedOrder.deliveryAddress}")
                    }
                    Row {
                        Text(
                            fontSize = 14.sp,
                            text = "Color: ${receivedOrder.products.productColor} ")
                    }
                    Row {
                        Text(
                            fontSize = 14.sp,
                            text = "Specs: ${receivedOrder.products.productSpecs} ")
                    }
                    Row {
                        Text(
                            fontSize = 14.sp,
                            text = "Status: ${receivedOrder.status} ")
                    }


                    // Buttons
                    Row {

                        OutlinedButton(
                            shape = RoundedCornerShape(5.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                                .padding(top = 8.dp, end = 4.dp),
                            onClick = { }) {
                            Text(text = "Cancel")

                        }
                        Button(
                            shape = RoundedCornerShape(5.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                                .padding(start = 4.dp, top = 8.dp),
                            onClick = {
                                onClick()
                            }) {
                            Text(text = "Confirm")

                        }


                    }
                }
            }
        }

    }




}


@Composable
fun ProcessingOrders(receivedOrder: AdminReceivedOrder, onClick: () -> Unit) {
    val expandableState = remember{ mutableStateOf(false) }
    val cardHeight by animateDpAsState(targetValue = if (expandableState.value) 310.dp else 100.dp )


    Card(
        onClick = {
            if(!expandableState.value) {

                expandableState.value = true
            }
            else {
                expandableState.value = false
            }
        },
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
            .height(cardHeight)
    ) {

        Column1(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxSize()
        ) {



            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .weight(0.4f)
                    .fillMaxSize()
                    .padding(start = 10.dp)
            ) {

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Card(
                        modifier = Modifier
                            .size(80.dp)
                            .padding(),
                        shape = RoundedCornerShape(10.dp),
                    ) {
                        AsyncImage(
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(1.dp),
                            model = receivedOrder.products.productIconUrl,
                            contentDescription = ""
                        )
                    }
                    Column1(
                        modifier = Modifier.padding(horizontal = 8.dp)
                    ) {
                        Text(
                            fontWeight = FontWeight.Bold,
                            text = receivedOrder.products.productName
                        )
                        Text(
                            fontWeight = FontWeight.Bold,
                            text = receivedOrder.products.productCategory
                        )
                        Text(
                            text = "Received: ${
                                receivedOrder.orderPlacedOn.toInstant().toString().dropLast(14)
                            } "
                        )


                    }
                }

                Text(
                    fontWeight = FontWeight.Bold,
                    text = "AU$ ${receivedOrder.products.productCost.toString()}"
                )

            }

            if(expandableState.value) {
                Column1(
                    modifier = Modifier
                        .padding(8.dp)
                        .weight(0.8f)
                        .fillMaxSize()
                ){
                    Row {
                        Text(
                            fontSize = 14.sp,
                            text = "Deliver To: ${receivedOrder.deliverTo}")
                    }
                    Row {
                        Text(
                            fontSize = 14.sp,
                            text = "Contact Number: ${receivedOrder.contactNumber}")
                    }
                    Row {
                        Text(
                            fontSize = 14.sp,
                            text = "Address: ${receivedOrder.deliveryAddress}")
                    }
                    Row {
                        Text(
                            fontSize = 14.sp,
                            text = "Color: ${receivedOrder.products.productColor} ")
                    }
                    Row {
                        Text(
                            fontSize = 14.sp,
                            text = "Specs: ${receivedOrder.products.productSpecs} ")
                    }
                    Row {
                        Text(
                            fontSize = 14.sp,
                            text = "Status: ${receivedOrder.status} ")
                    }


                    // Buttons
                    Row {

                        OutlinedButton(
                            shape = RoundedCornerShape(5.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                                .padding(top = 8.dp, end = 4.dp),
                            onClick = { }) {
                            Text(text = "Cancel")

                        }
                        Button(
                            shape = RoundedCornerShape(5.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                                .padding(start = 4.dp, top = 8.dp),
                            onClick = {
                                onClick()
                            }) {
                            Text(text = "Confirm")

                        }


                    }
                }
            }
        }

    }




}


@Composable
fun CompletedOrders(receivedOrder: AdminReceivedOrder, onClick: () -> Unit) {
    val expandableState = remember{ mutableStateOf(false) }
    val cardHeight by animateDpAsState(targetValue = if (expandableState.value) 310.dp else 100.dp)


    Card(
        onClick = {
            if(!expandableState.value) {
                expandableState.value = true
            }
            else {
                expandableState.value = false
            }        },
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
            .height(cardHeight)
    ) {

        Column1(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxSize()
        ) {



            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .weight(0.4f)
                    .fillMaxSize()
                    .padding(start = 10.dp)
            ) {

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Card(
                        modifier = Modifier
                            .size(80.dp)
                            .padding(),
                        shape = RoundedCornerShape(10.dp),
                    ) {
                        AsyncImage(
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(1.dp),
                            model = receivedOrder.products.productIconUrl,
                            contentDescription = ""
                        )
                    }
                    Column1(
                        modifier = Modifier.padding(horizontal = 8.dp)
                    ) {
                        Text(
                            fontWeight = FontWeight.Bold,
                            text = receivedOrder.products.productName
                        )
                        Text(
                            fontWeight = FontWeight.Bold,
                            text = receivedOrder.products.productCategory
                        )
                        Text(
                            text = "Received: ${
                                receivedOrder.orderPlacedOn.toInstant().toString().dropLast(14)
                            } "
                        )


                    }
                }

                Text(
                    fontWeight = FontWeight.Bold,
                    text = "AU$ ${receivedOrder.products.productCost.toString()}"
                )

            }

            if(expandableState.value) {
                Column1(
                    modifier = Modifier
                        .padding(8.dp)
                        .weight(0.8f)
                        .fillMaxSize()
                ){
                    Row {
                        Text(
                            fontSize = 14.sp,
                            text = "Deliver To: ${receivedOrder.deliverTo}")
                    }
                    Row {
                        Text(
                            fontSize = 14.sp,
                            text = "Contact Number: ${receivedOrder.contactNumber}")
                    }
                    Row {
                        Text(
                            fontSize = 14.sp,
                            text = "Address: ${receivedOrder.deliveryAddress}")
                    }
                    Row {
                        Text(
                            fontSize = 14.sp,
                            text = "Color: ${receivedOrder.products.productColor} ")
                    }
                    Row {
                        Text(
                            fontSize = 14.sp,
                            text = "Specs: ${receivedOrder.products.productSpecs} ")
                    }
                    Row {
                        Text(
                            fontSize = 14.sp,
                            text = "Status: ${receivedOrder.status} ")
                    }


                    // Buttons
                    Row {

                        OutlinedButton(
                            shape = RoundedCornerShape(5.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                                .padding(top = 8.dp, end = 4.dp),
                            onClick = {
                                onClick()
                            }) {
                            Text(text = "Cancel and refund")

                        }



                    }
                }
            }
        }

    }




}


@Composable
fun RefundedOrders(receivedOrder: AdminReceivedOrder) {
    val expandableState = remember{ mutableStateOf(false) }
    val cardHeight by animateDpAsState(targetValue = if (expandableState.value) 250.dp else 100.dp)



    Card(
        onClick = {
            if(!expandableState.value) {
                expandableState.value = true
            }
            else {
                expandableState.value = false
            }
        },
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
            .height(cardHeight)
    ) {

        Column1(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 8.dp)
        ) {



            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .weight(0.4f)
                    .fillMaxSize()
                    .padding(start = 10.dp)
            ) {

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Card(
                        modifier = Modifier
                            .size(80.dp)
                            .padding(),
                        shape = RoundedCornerShape(10.dp),
                    ) {
                        AsyncImage(
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(1.dp),
                            model = receivedOrder.products.productIconUrl,
                            contentDescription = ""
                        )
                    }
                    Column1(
                        modifier = Modifier.padding(horizontal = 8.dp)
                    ) {
                        Text(
                            fontWeight = FontWeight.Bold,
                            text = receivedOrder.products.productName
                        )
                        Text(
                            fontWeight = FontWeight.Bold,
                            text = receivedOrder.products.productCategory
                        )
                        Text(
                            text = "Received: ${
                                receivedOrder.orderPlacedOn.toInstant().toString().dropLast(14)
                            } "
                        )


                    }
                }

                Text(
                    fontWeight = FontWeight.Bold,
                    text = "AU$ ${receivedOrder.products.productCost.toString()}"
                )

            }

            if(expandableState.value) {
                Column1(
                    modifier = Modifier
                        .padding(8.dp)
                        .weight(0.8f)
                        .fillMaxSize()
                ){
                    Row {
                        Text(
                            fontSize = 14.sp,
                            text = "Deliver To: ${receivedOrder.deliverTo}")
                    }
                    Row {
                        Text(
                            fontSize = 14.sp,
                            text = "Contact Number: ${receivedOrder.contactNumber}")
                    }
                    Row {
                        Text(
                            fontSize = 14.sp,
                            text = "Address: ${receivedOrder.deliveryAddress}")
                    }
                    Row {
                        Text(
                            fontSize = 14.sp,
                            text = "Color: ${receivedOrder.products.productColor} ")
                    }
                    Row {
                        Text(
                            fontSize = 14.sp,
                            text = "Specs: ${receivedOrder.products.productSpecs} ")
                    }
                    Row {
                        Text(
                            fontSize = 14.sp,
                            text = "Status: ${receivedOrder.status} ")
                    }


                }
            }
        }

    }




}


@Composable
fun addStockCountDialogue(navController:NavController,  products: Products, viewModel: AdminViewModel, stateChange :(Boolean)->Unit){
    val context = LocalContext.current

    val stock = remember {
        mutableStateOf("")
    }


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
                    text = "Add Review"
                )

                OutlinedTextField(
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                viewModel.updateStockCount(products, newStock = stock.value.toInt())
                                Toast.makeText(context, "Stock Count Updated", Toast.LENGTH_SHORT).show()
                                products.currentlyOnStock = stock.value.toInt()
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
                    value = stock.value, onValueChange = {
                        stock.value = it
                    })

            }
        }
    }
}