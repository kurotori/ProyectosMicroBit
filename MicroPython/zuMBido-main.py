from microbit import *
import radio
import machine


radio.config(length=250)
uart.init(baudrate=115200)

serial_buffer = ""

"""Permite enviar un texto a la consola serial
"""
def escribir(texto):
    uart.write(texto + '\r\n')



while True:
    if uart.any():
            # Obtener el primer caracter de la conexión
            char_bytes = uart.read(1)
            
            if char_bytes:
                # Conversión a caracteres de texto
                char = str(char_bytes, 'UTF-8')
                
                # Análisis: Si el caracter se corresponde con 'Enter' (\r o \n)
                #   se evalúa el comando recibido
                if char == '\n' or char == '\r':
                    if serial_buffer:
                        escribir('Recibido')