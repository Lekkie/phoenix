-- Message Type
INSERT INTO tbl_message_types (name, code, description, status, created_by, created_on) VALUES ('AuthRequest', '0100', 'Authorization Request', 1, 'System', CURRENT_TIMESTAMP())
INSERT INTO tbl_message_types (name, code, description, status, created_by, created_on) VALUES ('AuthRequest', '0101', 'Authorization Request Repeat', 1, 'System', CURRENT_TIMESTAMP())
INSERT INTO tbl_message_types (name, code, description, status, created_by, created_on) VALUES ('AuthResponse', '0110', 'Authorization Response', 1, 'System', CURRENT_TIMESTAMP())
INSERT INTO tbl_message_types (name, code, description, status, created_by, created_on) VALUES ('AuthAdvice', '0120', 'Authorization Advice', 1, 'System', CURRENT_TIMESTAMP())
INSERT INTO tbl_message_types (name, code, description, status, created_by, created_on) VALUES ('AuthAdvice', '0121', 'Authorization Advice Repeat', 1, 'System', CURRENT_TIMESTAMP())
INSERT INTO tbl_message_types (name, code, description, status, created_by, created_on) VALUES ('AuthAdviceResponse', '0130', 'Authorization Advice Response', 1, 'System', CURRENT_TIMESTAMP())
INSERT INTO tbl_message_types (name, code, description, status, created_by, created_on) VALUES ('TranRequest', '0200', 'Transaction Request', 1, 'System', CURRENT_TIMESTAMP())
INSERT INTO tbl_message_types (name, code, description, status, created_by, created_on) VALUES ('TranRequestRepeat', '0201', 'Transaction Request Repeat', 1, 'System', CURRENT_TIMESTAMP())
INSERT INTO tbl_message_types (name, code, description, status, created_by, created_on) VALUES ('TranResponse', '0210', 'Transaction Response', 1, 'System', CURRENT_TIMESTAMP())
INSERT INTO tbl_message_types (name, code, description, status, created_by, created_on) VALUES ('TranCompletion', '0202', 'Transaction Completion', 1, 'System', CURRENT_TIMESTAMP())
INSERT INTO tbl_message_types (name, code, description, status, created_by, created_on) VALUES ('TranCompletionRepeat', '0203', 'Transaction Completion Repeat', 1, 'System', CURRENT_TIMESTAMP())
INSERT INTO tbl_message_types (name, code, description, status, created_by, created_on) VALUES ('TranCompletionResponse', '0212', 'Transaction Completion Response', 1, 'System', CURRENT_TIMESTAMP())
INSERT INTO tbl_message_types (name, code, description, status, created_by, created_on) VALUES ('TranAdvice', '0220', 'Transaction Advice', 1, 'System', CURRENT_TIMESTAMP())
INSERT INTO tbl_message_types (name, code, description, status, created_by, created_on) VALUES ('TranAdviceRepeat', '0221', 'Transaction Advice  Repeat', 1, 'System', CURRENT_TIMESTAMP())
INSERT INTO tbl_message_types (name, code, description, status, created_by, created_on) VALUES ('TranAdviceResponse', '0230', 'Transaction Advice Response', 1, 'System', CURRENT_TIMESTAMP())
INSERT INTO tbl_message_types (name, code, description, status, created_by, created_on) VALUES ('AcqFileUpdateRequest', '0300', 'Acquirer File Update Request', 1, 'System', CURRENT_TIMESTAMP())
INSERT INTO tbl_message_types (name, code, description, status, created_by, created_on) VALUES ('AcqFileUpdateRequestRepeat', '0301', 'Acquirer File Update Request Repeat', 1, 'System', CURRENT_TIMESTAMP())
INSERT INTO tbl_message_types (name, code, description, status, created_by, created_on) VALUES ('AcqFileUpdateResponse', '0310', 'Acquirer File Update Response', 1, 'System', CURRENT_TIMESTAMP())
INSERT INTO tbl_message_types (name, code, description, status, created_by, created_on) VALUES ('AcqFileUpdateAdvice', '0320', 'Acquirer File Update Advice', 1, 'System', CURRENT_TIMESTAMP())
INSERT INTO tbl_message_types (name, code, description, status, created_by, created_on) VALUES ('AcqFileUpdateAdviceRepeat', '0321', 'Acquirer File Update Advice Repeat', 1, 'System', CURRENT_TIMESTAMP())
INSERT INTO tbl_message_types (name, code, description, status, created_by, created_on) VALUES ('AcqFileUpdateAdviceResponse', '0330', 'Acquirer File Update Advice Response', 1, 'System', CURRENT_TIMESTAMP())
INSERT INTO tbl_message_types (name, code, description, status, created_by, created_on) VALUES ('IssuerFileUpdateAdvice', '0322', 'Issuer File Update Advice', 1, 'System', CURRENT_TIMESTAMP())
INSERT INTO tbl_message_types (name, code, description, status, created_by, created_on) VALUES ('IssuerFileUpdateAdviceRepeat', '0323', 'Issuer File Update Advice Repeat', 1, 'System', CURRENT_TIMESTAMP())
INSERT INTO tbl_message_types (name, code, description, status, created_by, created_on) VALUES ('IssuerFileUpdateAdviceResponse', '0332', 'Issuer File Update Advice Response', 1, 'System', CURRENT_TIMESTAMP())
INSERT INTO tbl_message_types (name, code, description, status, created_by, created_on) VALUES ('ReversalRequest', '0400', 'Reversal Request', 1, 'System', CURRENT_TIMESTAMP())
INSERT INTO tbl_message_types (name, code, description, status, created_by, created_on) VALUES ('ReversalRequestRepeat', '0401', 'Reversal Request Repeat', 1, 'System', CURRENT_TIMESTAMP())
INSERT INTO tbl_message_types (name, code, description, status, created_by, created_on) VALUES ('ReversalResponse', '0410', 'Reversal Response', 1, 'System', CURRENT_TIMESTAMP())
INSERT INTO tbl_message_types (name, code, description, status, created_by, created_on) VALUES ('ReversalAdvice', '0420', 'Reversal Advice', 1, 'System', CURRENT_TIMESTAMP())
INSERT INTO tbl_message_types (name, code, description, status, created_by, created_on) VALUES ('ReversalAdviceRepeat', '0421', 'Reversal Advice Repeat', 1, 'System', CURRENT_TIMESTAMP())
INSERT INTO tbl_message_types (name, code, description, status, created_by, created_on) VALUES ('ReversalAdviceResponse', '0430', 'Reversal Advice Response', 1, 'System', CURRENT_TIMESTAMP())
--INSERT INTO tbl_message_types (name, code, description, status, created_by, created_on) VALUES ('AcqReconRequest', '0500', 'Acquirer Reconciliation Request', 1, 'System', CURRENT_TIMESTAMP())
--INSERT INTO tbl_message_types (name, code, description, status, created_by, created_on) VALUES ('AcqReconRequestRepeat', '0501', 'Acquirer Reconciliation Request Repeat', 1, 'System', CURRENT_TIMESTAMP())
--INSERT INTO tbl_message_types (name, code, description, status, created_by, created_on) VALUES ('AcqReconResponse', '0510', 'Acquirer Reconciliation Response', 1, 'System', CURRENT_TIMESTAMP())
--INSERT INTO tbl_message_types (name, code, description, status, created_by, created_on) VALUES ('IssuerReconRequest', '0502', 'Issuer Reconciliation Request', 1, 'System', CURRENT_TIMESTAMP())
--INSERT INTO tbl_message_types (name, code, description, status, created_by, created_on) VALUES ('IssuerReconRequestRepeat', '0503', 'Issuer Reconciliation Request  Repeat', 1, 'System', CURRENT_TIMESTAMP())
--INSERT INTO tbl_message_types (name, code, description, status, created_by, created_on) VALUES ('IssuerReconResponse', '0512', 'Issuer Reconciliation Response', 1, 'System', CURRENT_TIMESTAMP())
--INSERT INTO tbl_message_types (name, code, description, status, created_by, created_on) VALUES ('AcqReconAdvice', '0520', 'Acquirer Reconciliation Advice', 1, 'System', CURRENT_TIMESTAMP())
--INSERT INTO tbl_message_types (name, code, description, status, created_by, created_on) VALUES ('AcqReconAdviceRepeat', '0521', 'Acquirer Reconciliation Advice Repeat', 1, 'System', CURRENT_TIMESTAMP())
--INSERT INTO tbl_message_types (name, code, description, status, created_by, created_on) VALUES ('AcqReconAdviceResponse', '0530', 'Acquirer Reconciliation Advice Response', 1, 'System', CURRENT_TIMESTAMP())
--INSERT INTO tbl_message_types (name, code, description, status, created_by, created_on) VALUES ('IssuerReconAdvice', '0522', 'Issuer Reconciliation Advice', 1, 'System', CURRENT_TIMESTAMP())
--INSERT INTO tbl_message_types (name, code, description, status, created_by, created_on) VALUES ('IssuerReconAdviceRepeat', '0523', 'Issuer Reconciliation Advice Repeat', 1, 'System', CURRENT_TIMESTAMP())
--INSERT INTO tbl_message_types (name, code, description, status, created_by, created_on) VALUES ('IssuerReconAdviceResponse', '0532', 'Issuer Reconciliation Advice Response', 1, 'System', CURRENT_TIMESTAMP())
INSERT INTO tbl_message_types (name, code, description, status, created_by, created_on) VALUES ('AdminRequest', '0600', 'Administration Request', 1, 'System', CURRENT_TIMESTAMP())
INSERT INTO tbl_message_types (name, code, description, status, created_by, created_on) VALUES ('AdminRequestRepeat', '0601', 'Administration Request Repeat', 1, 'System', CURRENT_TIMESTAMP())
INSERT INTO tbl_message_types (name, code, description, status, created_by, created_on) VALUES ('AdminResponse', '0610', 'Administration Response', 1, 'System', CURRENT_TIMESTAMP())
INSERT INTO tbl_message_types (name, code, description, status, created_by, created_on) VALUES ('AdminAdvice', '0620', 'Administration Advice', 1, 'System', CURRENT_TIMESTAMP())
INSERT INTO tbl_message_types (name, code, description, status, created_by, created_on) VALUES ('AdminAdviceRepeat', '0621', 'Administration Advice Repeat', 1, 'System', CURRENT_TIMESTAMP())
INSERT INTO tbl_message_types (name, code, description, status, created_by, created_on) VALUES ('AdminAdviceResponse', '0630', 'Administration Advice Response', 1, 'System', CURRENT_TIMESTAMP())
INSERT INTO tbl_message_types (name, code, description, status, created_by, created_on) VALUES ('NetworkMgtRequest', '0800', 'Network Management Request', 1, 'System', CURRENT_TIMESTAMP())
INSERT INTO tbl_message_types (name, code, description, status, created_by, created_on) VALUES ('NetworkMgtRequestRepeat', '0801', 'Network Management Request Repeat', 1, 'System', CURRENT_TIMESTAMP())
INSERT INTO tbl_message_types (name, code, description, status, created_by, created_on) VALUES ('NetworkMgtResponse', '0810', 'Network Management Response', 1, 'System', CURRENT_TIMESTAMP())



-- Transaction Type
INSERT INTO tbl_transaction_types (name, code, description, status, created_by, created_on) VALUES ('Purchase', '00', 'Goods and Services', 1, 'System', CURRENT_TIMESTAMP())
INSERT INTO tbl_transaction_types (name, code, description, status, created_by, created_on) VALUES ('CashWithdrawal', '01', 'Cash Withdrawal', 1, 'System', CURRENT_TIMESTAMP())
INSERT INTO tbl_transaction_types (name, code, description, status, created_by, created_on) VALUES ('PurchaseCashBack', '09', 'Goods and Services  with Cashback', 1, 'System', CURRENT_TIMESTAMP())
INSERT INTO tbl_transaction_types (name, code, description, status, created_by, created_on) VALUES ('Deposit', '21', 'Deposit', 1, 'System', CURRENT_TIMESTAMP())
INSERT INTO tbl_transaction_types (name, code, description, status, created_by, created_on) VALUES ('Balance', '31', 'Balance inquiry', 1, 'System', CURRENT_TIMESTAMP())
INSERT INTO tbl_transaction_types (name, code, description, status, created_by, created_on) VALUES ('MiniStatement', '38', 'Mini-Statement inquiry', 1, 'System', CURRENT_TIMESTAMP())
INSERT INTO tbl_transaction_types (name, code, description, status, created_by, created_on) VALUES ('LinkedInquiry', '39', 'Linked account inquiry', 1, 'System', CURRENT_TIMESTAMP())
INSERT INTO tbl_transaction_types (name, code, description, status, created_by, created_on) VALUES ('Transfer', '40', 'Cardholder accounts transfer', 1, 'System', CURRENT_TIMESTAMP())
INSERT INTO tbl_transaction_types (name, code, description, status, created_by, created_on) VALUES ('AccountPayment', '50', 'Payment from account', 1, 'System', CURRENT_TIMESTAMP())
INSERT INTO tbl_transaction_types (name, code, description, status, created_by, created_on) VALUES ('CardOnHold', '90', 'Place card on hold', 1, 'System', CURRENT_TIMESTAMP())
INSERT INTO tbl_transaction_types (name, code, description, status, created_by, created_on) VALUES ('GeneralAdmin', '91', 'GeneralAdmin (See extended transaction type', 1, 'System', CURRENT_TIMESTAMP())
INSERT INTO tbl_transaction_types (name, code, description, status, created_by, created_on) VALUES ('PinChange', '92', 'Change PIN', 1, 'System', CURRENT_TIMESTAMP())


-- Account Types
INSERT INTO tbl_account_types (name, code, description, status, created_by, created_on) VALUES ('Default', '00', 'Default account type', 1, 'System', CURRENT_TIMESTAMP())
INSERT INTO tbl_account_types (name, code, description, status, created_by, created_on) VALUES ('Savings', '10', 'Savings account type', 1, 'System', CURRENT_TIMESTAMP())
INSERT INTO tbl_account_types (name, code, description, status, created_by, created_on) VALUES ('Current', '20', 'Current account type', 1, 'System', CURRENT_TIMESTAMP())
INSERT INTO tbl_account_types (name, code, description, status, created_by, created_on) VALUES ('Credit', '30', 'Credit account type', 1, 'System', CURRENT_TIMESTAMP())

-- Card Set
INSERT INTO tbl_card_sets (name, description, status, created_by, created_on) VALUES ('DefaultCardSet', 'Default card set', 1, 'System', CURRENT_TIMESTAMP())

-- Card Set Account Type
INSERT INTO tbl_card_set_account_types (card_set_id, account_type_id, created_by, created_on) VALUES (1, 1, 'System', CURRENT_TIMESTAMP())
INSERT INTO tbl_card_set_account_types (card_set_id, account_type_id, created_by, created_on) VALUES (1, 2, 'System', CURRENT_TIMESTAMP())
INSERT INTO tbl_card_set_account_types (card_set_id, account_type_id, created_by, created_on) VALUES (1, 3, 'System', CURRENT_TIMESTAMP())
INSERT INTO tbl_card_set_account_types (card_set_id, account_type_id, created_by, created_on) VALUES (1, 4, 'System', CURRENT_TIMESTAMP())

-- Routing Group
INSERT INTO tbl_routing_groups (name, description, status, created_by, created_on) VALUES ('DefaultRoutingGroup', 'Default routing group', 1, 'System', CURRENT_TIMESTAMP())

-- Totals Group
INSERT INTO tbl_totals_groups (name, description, status, created_by, created_on) VALUES ('DefaultTotalsGroup', 'Default totals group', 1, 'System', CURRENT_TIMESTAMP())


-- Card Set Bin (test purpose)
INSERT INTO tbl_card_set_bins (card_set_id, bin, created_by, created_on) VALUES  (1, '4', 'System', CURRENT_TIMESTAMP())
INSERT INTO tbl_card_set_bins (card_set_id, bin, created_by, created_on) VALUES  (1, '5', 'System', CURRENT_TIMESTAMP())
INSERT INTO tbl_card_set_bins (card_set_id, bin, created_by, created_on) VALUES  (1, '6', 'System', CURRENT_TIMESTAMP())

-- Card Acceptor (test purpose)
INSERT INTO tbl_card_acceptors (name, code, address, phone, state, routing_group_id, description, status, created_by, created_on) VALUES ('Test Merchant', 'MERCHANT0000001', 'Flat 2, Swan House, High Street, MK401RN, Bedford', '+2348032296229','Bedfordshire', 1, 'Test merchant', 1, 'System', CURRENT_TIMESTAMP())

-- Card Product (test purpose)
INSERT INTO tbl_card_products (name, card_set_id, totals_group_id, description, status, created_by, created_on) VALUES ('Test All Cards Product', 1, 1, 'All cards belong here', 1, 'System', CURRENT_TIMESTAMP())

-- Route by Card (test purpose)
INSERT INTO tbl_route_by_cards (routing_group_id, totals_group_id, transaction_group_id, sink_node_id, description, status, created_by, created_on) VALUES (1, 1, 0, 2, 'Default route for all cards', 1, 'System', CURRENT_TIMESTAMP())

-- Route by Receiving Inst Id (test purpose)
INSERT INTO tbl_route_by_receiving_inst_id (routing_group_id, receiving_inst_id, sink_node_id, description, status, created_by, created_on) VALUES (1, '628051010', 2, 'Route for 628051010', 1, 'System', CURRENT_TIMESTAMP())

-- Nodes (test purpose)
INSERT INTO tbl_nodes (name, type, validate_exp_date, routing_group_id, key_id, description, status, created_by, created_on) VALUES ('srcSim1', 0, 1, 1, 0, 'Test src node', 1, 'System', CURRENT_TIMESTAMP())
INSERT INTO tbl_nodes (name, type, validate_exp_date, routing_group_id, key_id, description, status, created_by, created_on) VALUES ('snkSim1', 1, 1, 0, 0, 'Test sink node', 1, 'System', CURRENT_TIMESTAMP())
INSERT INTO tbl_nodes (name, type, validate_exp_date, routing_group_id, key_id, description, status, created_by, created_on) VALUES ('srcSim2', 0, 1, 1, 0, 'Test src node', 1, 'System', CURRENT_TIMESTAMP())
INSERT INTO tbl_nodes (name, type, validate_exp_date, routing_group_id, key_id, description, status, created_by, created_on) VALUES ('snkSim2', 1, 1, 0, 0, 'Test sink node', 1, 'System', CURRENT_TIMESTAMP())

-- TCP Endpoints (test purpose)
INSERT INTO tbl_tcp_endpoints (name, ip, port, server, description, status, created_by, created_on) VALUES ('TestIP1', '0.0.0.0', 50000, true, 'Test server', 1, 'System', CURRENT_TIMESTAMP())
--INSERT INTO tbl_tcp_endpoints (name, ip, port, server, description, status, created_by, created_on) VALUES ('TestIP3', '0.0.0.0', 50001, true, 'Test server2', 1, 'System', CURRENT_TIMESTAMP())
INSERT INTO tbl_tcp_endpoints (name, ip, port, server, description, status, created_by, created_on) VALUES ('TestIP2', '127.0.0.1', 60000, false, 'Test clients', 1, 'System', CURRENT_TIMESTAMP())
--INSERT INTO tbl_tcp_endpoints (name, ip, port, server, description, status, created_by, created_on) VALUES ('TestIP4', '127.0.0.1', 60001, false, 'Test clients', 1, 'System', CURRENT_TIMESTAMP())

-- SAP Endpoints (test purpose)
INSERT INTO tbl_sap_endpoints (name, src_node_id, snk_node_id, tcp_endpoint_id, iso_bridge_id, description, status, created_on, created_by) VALUES ('Sim1PB', 1, 0, 1, 1,'Test Interchange Bridge', 1, CURRENT_TIMESTAMP(), 'System')
INSERT INTO tbl_sap_endpoints (name, src_node_id, snk_node_id, tcp_endpoint_id, iso_bridge_id, description, status, created_on, created_by) VALUES ('Sim2PB', 0, 2, 2, 1,'Test Interchange Bridge', 1, CURRENT_TIMESTAMP(), 'System')



