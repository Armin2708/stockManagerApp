import ClientCard from "./ClientCard.jsx";
import {
    Box,
    Button, Checkbox,
    Table,
    TableCaption,
    TableContainer,
    Tbody,
    Td,
    Text,
    Tfoot,
    Th,
    Thead,
    Tr
} from "@chakra-ui/react";
import DetailsClientButton from "./DetailsClientButton.jsx";
import UpdateClientButton from "./UpdateClientButton.jsx";
import DeleteClientButton from "./DeleteClientButton.jsx";
import React, {useEffect, useState} from "react";
import {getInsuranceClient, getInsuranceClients} from "../services/insuranceClient.js";
import {errorNotification} from "../services/notification.js";
import axios from "axios";
import CreateClientDrawer from "./CreateClientButton.jsx";
import {ArrowLeftIcon, ArrowRightIcon} from "@chakra-ui/icons";
import GenerateClientButton from "./GenerateClientButton.jsx";

export default function ClientList({clients,isLoading,error,pageNumber,setPageNumber,maxPageNumber}){

    const [selectedIds,setSelectedIds] = useState([]);
    const [disable,setDisable] = useState(true);
    const [disableDelete,setDisableDelete] = useState(true)
    const [selectedClient,setSelectedClient]= useState({})
    const [disableCreate,setDisableCreate] = useState(false)


    useEffect(() => {
        if (selectedIds.length===1){
            setDisable(false);
            setDisableDelete(false)
            setDisableCreate(true)
            getInsuranceClient(selectedIds[0]).then(res =>{
                setSelectedClient(res.data)})
        }
        else if (selectedIds.length===0){
            setDisableDelete(true);
            setDisable(true);
            setDisableCreate(false)
            setSelectedClient({});
        }
        else{
            setDisable(true);
            setDisableCreate(true)
            setDisableDelete(false);
            setSelectedClient({});

        }

    }, [selectedIds]);

    const handleCheck = (clients) => {
        setSelectedIds(prevSelectedIds => [
            ...prevSelectedIds,
            ...clients
                .filter(client => !prevSelectedIds
                .includes(client.id))
                .map(client => client.id)
        ]);
    }

    const handleUnCheck = () =>{
        setSelectedIds([]);
    }

    const event = (e) => {
        if (e.target.checked) {
            console.log(clients)
            handleCheck(clients);
            console.log(selectedIds)
        } else {
            handleUnCheck();
            console.log(selectedIds)
        }
    }


    return(
        <Box borderWidth='2px' borderRadius='lg' overflow='hidden'  >
            <TableContainer>
                <Table variant='striped' colorScheme='gray'>
                    <TableCaption>Page {pageNumber}</TableCaption>
                    <Thead>
                        <Tr>
                            <Th>
                                <Text>Select All</Text>
                                <Checkbox
                                    onChange={event}
                                    isChecked={selectedIds?.length===clients?.length}
                                />
                            </Th>
                            <Th>Title</Th>
                            <Th>Full Name</Th>
                            <Th isNumeric>Country</Th>
                        </Tr>
                    </Thead>
                    <Tbody>
                        { (clients?.length>0 && !error) ?
                                clients.map((client, index) => (
                                        <ClientCard
                                            key={index}
                                            client={client}
                                            selectedIds={selectedIds}
                                            setSelectedIds={setSelectedIds}
                                        />
                                    )
                                )
                            :
                            <Tr>
                                <Td>
                                    <Text>{error ? `${error}` : "No Clients Found"}</Text>
                                </Td>
                            </Tr>
                        }
                    </Tbody>
                    <Tfoot>
                        <Tr>
                            <Th>
                                <Button
                                    _hover={{
                                        transform: 'translateY(-2px)',
                                        boxShadow: 'lg'
                                    }}
                                    onClick={()=>{setPageNumber((pageNumber) => (pageNumber-1))}}
                                    isDisabled={pageNumber===1}

                                >
                                    {<ArrowLeftIcon/>}
                                </Button>
                            </Th>
                            <Th>
                                <CreateClientDrawer disable={disableCreate}/>
                            </Th>

                            <Th>
                                <DeleteClientButton
                                    disable={disableDelete}
                                    selectedIds={selectedIds}
                                    setSelectedIds={setSelectedIds}
                                    {...selectedClient}
                                />
                            </Th>
                            <Th>
                                <GenerateClientButton/>
                            </Th>
                            <Th>
                                <Button
                                    _hover={{
                                        transform: 'translateY(-2px)',
                                        boxShadow: 'lg'
                                    }}
                                    onClick={()=>{setPageNumber((pageNumber) => (pageNumber+1))}}
                                    isDisabled={pageNumber===maxPageNumber}
                                >
                                    {<ArrowRightIcon/>}

                                </Button>
                            </Th>
                        </Tr>
                    </Tfoot>
                </Table>
            </TableContainer>
        </Box>
    )
}