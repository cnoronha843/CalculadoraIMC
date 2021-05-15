package br.edu.infnet.caculadoraimc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import br.edu.infnet.caculadoraimc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bt_calcular = binding.btCalcular
        val mensagem = binding.mensagem

        bt_calcular.setOnClickListener {
            val edit_peso = binding.editPeso.text.toString()
            val edit_altura = binding.editAltura.text.toString()

            if (edit_peso.isEmpty()){
                mensagem.setText("Informe o seu peso")
            } else if (edit_altura.isEmpty()){
                mensagem.setText("Informe a sua altura")
            } else {
                CalculoImc()
            }
        }
    }

    private fun CalculoImc() {
        val pesoId = binding.editPeso
        val alturaId = binding.editAltura

        val peso = Integer.parseInt(pesoId.text.toString())
        val altura = java.lang.Float.parseFloat(alturaId.text.toString())
        val imc = peso / (altura * altura)
        val resultado = binding.mensagem

        val Mensagem = when {
            imc <= 18.5 -> "Peso Baixo"
            imc <= 24.9 -> "Peso Normal"
            imc <= 29.9 -> "Peso Sobrepeso"
            imc <= 34.9 -> "Obesidade (Grau 1)"
            imc <= 39.9 -> "Obesidade (Grau 2)"
            else -> "Obesidade Mórbida (Grau 3)"
        }
        imc.toString()
        resultado.setText("IMC: $imc \n $Mensagem")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflate = menuInflater
        inflate.inflate(R.menu.menu_principal, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.reset ->{
                val limparEditPeso = binding.editPeso
                val limparEditAltura = binding.editAltura
                val limparEditMensagem = binding.mensagem

                limparEditPeso.setText("")
                limparEditAltura.setText("")
                limparEditMensagem.setText("")
            }
        }
        return super.onOptionsItemSelected(item)
    }
}