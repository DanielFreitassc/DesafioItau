# Documentação de Configuração de VLANs e Portas de Acesso no Switch Cisco

## Informações Gerais

Esta configuração foi realizada em um **Switch Cisco WS-C3560-24PS** com **Cisco IOS Software, C3560 Software (C3560-ADVIPSERVICESK9-M), Version 12.2(37)SE1**.

A configuração inclui a criação e atribuição de VLANs, configuração de portas de acesso e definição de endereços IP para as VLANs.

---

## Sumário

1. [Inicialização e Acesso ao Modo de Configuração](#inicialização-e-acesso-ao-modo-de-configuração)
2. [Configuração das VLANs](#configuração-das-vlans)
    - VLAN 10
    - VLAN 11
    - VLAN 12
3. [Configuração de Endereços IP nas VLANs](#configuração-de-endereços-ip-nas-vlans)
4. [Configuração de Portas de Acesso](#configuração-de-portas-de-acesso)
5. [Finalização e Salvamento da Configuração](#finalização-e-salvamento-da-configuração)

---

## Inicialização e Acesso ao Modo de Configuração

1. Após a inicialização do switch, habilite o modo privilegiado:
    ```bash
    Switch> enable
    ```

2. Entre no modo de configuração global:
    ```bash
    Switch# configure terminal
    ```

---

## Configuração das VLANs

### VLAN 10

1. Crie e configure a **VLAN 10**:
    ```bash
    Switch(config)# interface vlan 10
    Switch(config-if)# ip address 192.168.0.254 255.255.255.0
    Switch(config-if)# exit
    ```

### VLAN 11

2. Crie e configure a **VLAN 11**:
    ```bash
    Switch(config)# interface vlan 11
    Switch(config-if)# ip address 192.168.1.254 255.255.255.0
    Switch(config-if)# ip helper-address 192.168.0.1
    Switch(config-if)# exit
    ```

### VLAN 12

3. Crie e configure a **VLAN 12**:
    ```bash
    Switch(config)# interface vlan 12
    Switch(config-if)# ip address 192.168.2.254 255.255.255.0
    Switch(config-if)# ip helper-address 192.168.0.1
    Switch(config-if)# exit
    ```

---

## Configuração de Endereços IP nas VLANs

1. Para cada VLAN criada, o endereço IP foi configurado conforme mostrado nas seções acima.

    | VLAN | Endereço IP           | Máscara Sub-rede   |
    |------|-----------------------|--------------------|
    | 10   | 192.168.0.254         | 255.255.255.0      |
    | 11   | 192.168.1.254         | 255.255.255.0      |
    | 12   | 192.168.2.254         | 255.255.255.0      |

2. Adicionalmente, para as VLANs 11 e 12, foi configurado um **helper-address**:
    ```bash
    ip helper-address 192.168.0.1
    ```

---

## Configuração de Portas de Acesso

1. Configure as portas **FastEthernet 0/1 e 0/2** para acesso na **VLAN 11**:
    ```bash
    Switch(config)# interface range fastEthernet 0/1 - 2
    Switch(config-if-range)# switchport mode access
    Switch(config-if-range)# switchport access vlan 11
    ```

2. Configure a porta **FastEthernet 0/24** para acesso na **VLAN 12**:
    ```bash
    Switch(config)# interface fastEthernet 0/24
    Switch(config-if)# switchport mode access
    Switch(config-if)# switchport access vlan 12
    ```

3. Configure a porta **GigabitEthernet 0/1** para acesso na **VLAN 10**:
    ```bash
    Switch(config)# interface gigabitEthernet 0/1
    Switch(config-if)# switchport mode access
    Switch(config-if)# switchport access vlan 10
    ```

---

## Finalização e Salvamento da Configuração

1. Finalize a configuração e retorne ao modo privilegiado:
    ```bash
    Switch(config)# end
    ```

2. Salve a configuração para garantir que as mudanças persistam após uma reinicialização:
    ```bash
    Switch# write memory
    ```

---

## Resumo

- VLANs configuradas: 10, 11 e 12
- Portas de acesso configuradas: FastEthernet 0/1, 0/2, 0/24, GigabitEthernet 0/1
- Helper-address configurado para as VLANs 11 e 12
- Configuração salva no switch

---
