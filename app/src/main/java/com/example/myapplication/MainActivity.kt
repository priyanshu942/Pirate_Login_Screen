package com.example.myapplication

import android.graphics.Paint
import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                   Login()
                }
            }
        }
    }
}

@Composable
fun Login() {
    var username by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    Box(modifier = Modifier.fillMaxSize())
    {
      Image(painter = painterResource(id = R.drawable.wp7866096),
          contentDescription ="Zorro",
          modifier = Modifier
              .fillMaxSize()
              .fillMaxHeight()
              .fillMaxWidth()
              .blur(6.dp),
          contentScale = ContentScale.Crop
      )

        Box(modifier = Modifier
            .fillMaxSize()
            .padding(25.dp)
            .alpha(0.5f)
            .clip(CutCornerShape(16.dp))
            .background(MaterialTheme.colors.background))

    }
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(50.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
        ) {
            LoginHeader()
            LoginField( username,
                password ,
                onusernamechange =
                {
                username=it
                },
                onpasswordchange =
                {
                    password=it
                },
            onforgotpassword = {

            })
        LoginFooter(onsigninclick = {

            },
            onsignupclick ={

            })
    }

}

@Composable
fun LoginHeader()
{
    Column(horizontalAlignment = Alignment.CenterHorizontally) {


        Text(
            text = "Welcome Pirate ", fontSize = 35.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Text(
            text = "Sign In to continue", fontSize = 20.sp,
            modifier = Modifier.padding(5.dp)
        )
    }
}

@Composable
fun ColumnScope.LoginField(username:String,password:String,
               onusernamechange:(String)->Unit,
               onpasswordchange:(String)->Unit,
                onforgotpassword: ()->Unit
) {
    Column() {


        DemoField(value = username,
            onChange = onusernamechange,
            label = "Username",
            placeholder = "Enter your email",
            leadingIcon = {
                Icon(Icons.Default.Email, contentDescription = "Email Icon")
            }
        , keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Next)
        )
        Spacer(modifier = Modifier.height(10.dp))
        DemoField(value = password,
            onChange = onpasswordchange,
            label = "Password",
            placeholder = "Enter your password here",
            visualtransform = PasswordVisualTransformation(),
            leadingIcon = {
                Icon(Icons.Default.Lock, contentDescription = "Lock")
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Go)
        )

        TextButton(
            onClick = onforgotpassword,
            modifier = Modifier.align(Alignment.End),
             ) {
            Text(text = "Forgot Password", color = Color.Blue)
        }
    }
}

@Composable
fun LoginFooter(
    onsigninclick:()->Unit,
    onsignupclick:()->Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {


        Button(
            onClick = onsigninclick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Sign In")
        }
        TextButton(onClick = onsignupclick) {
            Text(text = "Dont have an account?")
            Text(
                buildAnnotatedString {
                    withStyle(style = SpanStyle(color = Color.Blue))
                    {
                        append("click here")
                    }

                }, style = TextStyle(textDecoration = TextDecoration.Underline)
            )
        }

    }
}

@Composable
fun DemoField( value:String,
               onChange:(String)->Unit,
               label:String,
                placeholder:String,
                visualtransform:VisualTransformation= VisualTransformation.None,
               leadingIcon: @Composable (()-> Unit)?=null,
               trailingIcon: @Composable (()-> Unit)?=null,
               keyboardOptions: KeyboardOptions= KeyboardOptions.Default
                )
{
OutlinedTextField(value = value,
    onValueChange =onChange,
    label = {
        Text(text = label)
    },
    placeholder={
        Text(text = placeholder)
    },
    visualTransformation = visualtransform,
    keyboardOptions=keyboardOptions,
    leadingIcon=leadingIcon,
    trailingIcon=trailingIcon

)
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
   Login()
    }
}