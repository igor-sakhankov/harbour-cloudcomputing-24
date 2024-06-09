openapi: 3.0.1
info:
  title: Cash Wallet API
  version: 1.0.0
servers:
  - url: http://localhost:8080
paths:
  /v1/wallet/transaction:
    post:
      summary: Create a new transaction
      operationId: createTransaction
      requestBody:
        required: true
        content:
          application/json:
            schema:
              $ref: '#/components/schemas/TransactionRequest'
      responses:
        '200':
          description: Successful transaction creation
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/GenericResponse'
components:
  schemas:
    GenericResponse:
      type: object
      properties:
        data:
          type: object
        error:
          type: string
          nullable: true
    TransactionResponse:
      type: object
      properties:
        transactionId:
          type: string
        status:
          type: string
    TransactionRequest:
      type: object
      properties:
        amount:
          type: number
          format: double
        currency:
          type: string
        description:
          type: string
        userId:
          type: string
