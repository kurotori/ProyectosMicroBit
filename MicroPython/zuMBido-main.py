from microbit import *
import radio
import machine

#ID de la placa
id_placa = "".join("{:02x}".format(b) for b in machine.unique_id())

# Configuración del canal de radio y tamaño de paquete
radio.config(channel=7, length=250)
radio.on()

# Configuración de comunicación serial con la PC
uart.init(baudrate=115200)

buffer_serial = ""

def escribir(texto):
    uart.write(texto + '\r\n')
    
def enviarRadio(mensaje):
    radio.send(mensaje)
    parpadear(4,0,9,100)

def parpadear(xLed, yLed, intensidad, tiempo):
    fin = running_time() + tiempo
    while(running_time()<fin):
        display.set_pixel(xLed,yLed,intensidad);
    display.set_pixel(xLed,yLed,0);
    

def evaluarComando(comando):
    if(comando=="iniciar"):
       escribir("bid:"+id_placa)
       escribir("c:gr")
    #if(comando=="gr"):
        
       
        

while True:
    # -------------------------------------------------------------
    # 1. RADIO -> SERIAL: Mensajes recibidos de otros micro:bits
    # -------------------------------------------------------------
    mensaje_radio = radio.receive()
    if mensaje_radio:
        # Reenvía el mensaje directamente a la PC terminado en un salto de línea
        uart.write(mensaje_radio + '\n')

    # -------------------------------------------------------------
    # 2. SERIAL -> RADIO: Comandos enviados desde la app en Java
    # -------------------------------------------------------------
    if uart.any():
        char_bytes = uart.read(1)
        if char_bytes:
            char = str(char_bytes, 'utf-8')
            
            # Detectar fin de comando (\n o \r)
            if char == '\n' or char == '\r':
                comando = buffer_serial.strip()
                if comando:
                    # Emitir el comando a la red RF
                    escribir("recibido:" + comando)
                    evaluarComando(comando)
                    # radio.send(comando)
                    enviarRadio(comando)
                    buffer_serial = ""
            else:
                buffer_serial += char

    sleep(10)