When app is starting up, start all endpoints
Ability to disconnect any client endpoint 
Ability to reconnect any client endpoint
Ability to stop any server endpoint 
Ability to open any server endpoint
API to chk endpoint status

Ensure Client can connect to server

if stan is present, leave otherwise put urs

Read ISO from bytes
    Display ISO up to where it was readable if it fails to decode

Create SAPEndpoints - done
	both server & client connections - done
	one src node to receive  incoming requests - done
	one snk node to send outgoing requests - done
	if connection is client, (i.e i am server), ensure to know a way to know both client ip & port give option of sending response to src IP & port or just IP. - feature not available yet. use multimap to store client/channel
    
Create interface bridge package - done
    when interface loads, register to Database, so that it can be available to sapendpoint for pickup - done
    link sapendpoint to interchange - done
    inside bridge allow transformation from one interface to another - done
    let postbridge be the default - done
    from bridge to tm - done
    from tm to bridge - done
 
Find out why DE127 message length is missing - done

Create routing - done, test
    // route to a sink node
    // routing rule/order
    // 1. Recv Instd Id
    // 2. By a grp of elements (src node, Card acceptor ID)
    // 3. By Card BIN
    // if none found, return 92
    Get recvid from req add either cardaccptId (when its not defaultroutinggrp) or srcnode, if present use to route
        if route exists 
            ensure it is sinknode
            go to sink
            return
        if route does not exist, 
            return 92
    if recvid does not exist in request get totals group from card Bin and accountType, add either cardaccptId (when its not defaultroutinggrp) or srcnode
        if route exist
            ensure it is sinknode
            go to sink node
         if route does not exist, 
            return 92
    when assigning routes, route to sink node only - done
    always populate f127.3 (routing info) - done
 Todo - associate acquirer id with card acceptor, hence use to route trxn to sink node
    
Create tran manager
    validate request fields rules in tm 
        chk mandatory fields by transaction type
        chk card Luhn if it is required on Switch - done
        chk expiry if required if it is required for src node - done
        chk transaction type is allowed if it is required on Switch - done
        chk PIN if it is required for src node - on hold
        chk limit on src node(limit shld be on card acceptor) - on hold
    validate response fields in tm
        chk f39 has data
        chk 48 
    assign new Switch key to req - done
    put switch key back in response - done
    identify response with MTI, stan, trandatetime,acq, forwardingInstId - done 
    always generate ur own unique switch key, replace whatever is there - done
    
Time requests - done
Create rules table
    checkMandatoryFields
    checkCard / luhn check - done
    checkExpiry - done
    check message type - done
    check Tran Type - done
     
        
Do Key Management
    Store Keys
        KEK(ZMK)
        KWP(ZPK)
        BDK - DUKPT
        CAK - ARQC,AAC,TC,ARPC
        EMV-ECK
        EMK
        KVC - CVV
        KVP - IBM PIN, VISA PIN
        KWA
        
    Key Exchange process
    PIN Translation process
    
 Asoft Interface Integration
 Interswitch Interface Integration




github key
f565d5f9839ee13e17e2fb0163a254b545ae291d

