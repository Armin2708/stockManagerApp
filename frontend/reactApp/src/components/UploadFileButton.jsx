import {
    AlertDialog,
    AlertDialogBody,
    AlertDialogContent, AlertDialogFooter,
    AlertDialogHeader,
    AlertDialogOverlay,
    Button, Text, useDisclosure
} from "@chakra-ui/react";
import {AttachmentIcon, ViewIcon} from "@chakra-ui/icons";
import React from "react";
import UploadFile from "./UploadFile.jsx";

export default function UploadFileButton({client, disable}){
    const { isOpen, onOpen, onClose } = useDisclosure();
    const cancelRef = React.useRef();
    return(
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
                {<AttachmentIcon/>}
            </Button>

            <AlertDialog isOpen={isOpen} leastDestructiveRef={cancelRef} onClose={onClose}>
                <AlertDialogOverlay>
                    <AlertDialogContent>
                        <AlertDialogHeader fontSize="lg" fontWeight="bold">
                            Upload or Drag File
                        </AlertDialogHeader>

                        <AlertDialogBody>
                            <UploadFile client={client}/>
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
    )
}