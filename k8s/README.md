# kubernetes

## Detalhes de como rodar o projeto localmente

### Buildar a imagem do projeto

1. Buildar a imagem do projeto

```bash
docker build -t tech-challenge:local .
```

2. Verficar se a imagem foi criada corretamente

```bash
docker images | grep tech-challenge
```

3. Caso queira deletar a imagem

```bash
docker rmi tech-challenge:local
```

### Rodar o projeto localmente

#### Postgres

1. Rodar o pvc do Postgres

```bash
kubectl apply -f postgresql-pvc.yaml
```

2. Rodar os services do Postgres

```bash
kubectl apply -f postgresql-service.yaml
```

3. Rodar o deployment do Postgres

```bash
kubectl apply -f postgresql-deployment.yaml
```

#### Aplicação tech-challenge

kubectl exec -it tech-challenge-54b9d5c896-skdln -- /bin/bash

1. Rodar o service da aplicação

```bash
kubectl apply -f app-service.yaml
```

2. Rodar o deployment da aplicação

```bash
kubectl apply -f app-deployment.yaml
```

#### Escalar a aplicação

´´´bash
kubectl apply -f app-hpa.yaml
´´´

### Subir tudo

```bash
kubectl apply -f postgresql-pvc.yaml
kubectl apply -f postgresql-deployment.yaml
kubectl apply -f postgresql-service.yaml
kubectl apply -f app-deployment.yaml
kubectl apply -f app-service.yaml
kubectl apply -f app-hpa.yaml
kubectl apply -f metrics.yaml
```

## Acessar a aplicação

1. Local
   http://localhost:30007/swagger-ui/index.html#

2. Na nuvem
   http://<ip-do-node>:30007/swagger-ui/index.html#

## Verificar se os pods estão rodando corretamente

kubectl exec -it tech-challenge-794dcf5747-cb2tg -- /bin/bash

1. Verificar os pods

```bash
kubectl get pods
```

2. Verificar os services

```bash
kubectl get services
```

3. Verificar os deployments

```bash
kubectl get deployments
```

4. Verificar os pvc

```bash
kubectl get pvc
```

## Verificar os logs do pod

1. Verificar os logs da aplicação

```bash
kubectl logs -f <nome-do-pod>
```

## Deletar os recursos

Sempre deletar os deployments, services, hpa e pvc antes de deletar os pods

```bash
kubectl delete <tipo> <nome-do-pod>
```

### Deletar tudo

```bash
kubectl delete deployments --all -n default
kubectl delete replicasets --all -n default
kubectl delete statefulsets --all -n default
kubectl delete services --all -n default
kubectl delete pods --all -n default
kubectl delete pvc --all -n default
kubectl delete hpa --all -n default
```

## Buscar o IP do serviço

1. Buscar o IP do serviço

```bash
kubectl get nodes -o wide
```

2. Buscar a porta do serviço (PORT(S))

```bash
kubectl get services
```

## Testar escalabilidade

1. Comando para descrever o hpa e atualiza o consumo de CPU

```bash
kubectl describe hpa app-hpa
```

2. Comando para verificar o status do hpa

```bash
kubectl get hpa
```

3. Entrar no pod

```bash
kubectl exec -it tech-challenge-59855687f5-cfqxj  -- /bin/bash
```

4. Dentro do pod, executar o comando para consumir a CPU

```bash
stress -c 10
```
