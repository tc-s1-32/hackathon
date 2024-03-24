# Hackathon

## Diagramas Fase 1 (MVP)

1. Arquitetura da Cloud

![infra-hackaton-ARQ Cloud-Fase1](./infra-hackaton-ARQ%20Cloud-Fase1.drawio.png)

2. Arquitetura da Aplicação

![infra-hackaton-ARQ Aplicação-Fase1](./infra-hackaton-ARQ%20Aplicação-Fase1.drawio.png)

## Diagramas Fase 2

1. Arquitetura da Cloud

![infra-hackaton-ARQ Cloud-Fase2](./infra-hackaton-ARQ%20Cloud-Fase2.drawio.png)

2. Arquitetura da Aplicação

![infra-hackaton-ARQ Aplicação-Fase2](./infra-hackaton-ARQ%20Aplicação-Fase2.drawio.png)

Para a fase 2, consideramos adicionar uma lambda agendada para realizar os envios de noticações de lembretes.

Dado o aumento do volume de notificações, o Gmail já não seria uma ferramenta adequada e por isso consideramos usar o Amazon SES.
