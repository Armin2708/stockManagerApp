import React, {useState} from "react";
import { Document, Page } from 'react-pdf';
import {
    AlertDialog,
    AlertDialogBody,
    AlertDialogContent,
    AlertDialogFooter,
    AlertDialogHeader,
    AlertDialogOverlay,
    Button, Text, Image,
    useDisclosure,
} from "@chakra-ui/react";
import { errorNotification, successNotification } from "../services/notification.js";
import {deleteInsuranceClient, getInsuranceClient} from "../services/insuranceClient.js";
import {ViewIcon} from "@chakra-ui/icons";
import ClientFile from "./ClientFile.jsx";

function DetailsClientButton({client,disable}) {
    const { isOpen, onOpen, onClose } = useDisclosure();
    const cancelRef = React.useRef();


    return (
        <>
            <Button
                isDisabled={disable}
                _hover={{
                    transform: 'translateY(-2px)',
                    boxShadow: 'lg'
                }}
                variant={"unstyled"}
                onClick={onOpen}
            >
                {<ViewIcon/>}
            </Button>

            <AlertDialog isOpen={isOpen} leastDestructiveRef={cancelRef} onClose={onClose}>
                <AlertDialogOverlay>
                    <AlertDialogContent>
                        <AlertDialogHeader fontSize="lg" fontWeight="bold">
                            Details :
                        </AlertDialogHeader>

                        <AlertDialogBody>
                            <Text>
                                Full Name : {client.lastName} {client.firstName}
                            </Text>
                            <Text>
                                Gender : {client.gender}
                            </Text>
                            <Text>
                                Birth Date : {client.birthDate}
                            </Text>
                            <Text>
                                Phone Number : {client.phoneNumber}
                            </Text>
                            <Text>
                                Email : {client.email}
                            </Text>
                            <Text>
                                Full Address : {client.country} {client.state} {client.city} {client.postalCode} {client.street}
                            </Text>
                            <Text>
                                Age Risk : {client.ageRisk}
                            </Text>
                            <Text>
                                Health Risk : {client.healthRisk}
                            </Text>
                            <Text>
                                Job Risk : {client.jobRisk}
                            </Text>
                            <Text>
                                Living Area Risk : {client.livingAreaRisk}
                            </Text>
                            {(client.files?.length>0) ? client.files.map((file,index) =>(
                                <ClientFile
                                    key={index}
                                    file={file}/>
                            )) :
                                <Text>No Files Available</Text>}
                        </AlertDialogBody>

                        <AlertDialogFooter>
                            <Button colorScheme="teal" onClick={onClose} ml={3}>
                                close
                            </Button>
                        </AlertDialogFooter>
                    </AlertDialogContent>
                </AlertDialogOverlay>
            </AlertDialog>
        </>
    );
}

export default DetailsClientButton;
